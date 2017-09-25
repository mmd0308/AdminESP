package com.cnpc.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.cnpc.common.message.TokenErrorResponse;
import com.cnpc.common.message.TokenForbiddenResponse;
import com.cnpc.common.util.ClientUtil;
import com.cnpc.common.vo.LogInfo;
import com.cnpc.common.vo.PermissionInfo;
import com.cnpc.common.vo.UserInfo;
import com.cnpc.gateway.security.JwtTokenUtil;
import com.cnpc.gateway.feign.LogService;
import com.cnpc.gateway.feign.UserService;
import com.cnpc.gateway.utils.DBLog;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-21
 */
@RefreshScope
@Component
public class SessionAccessFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(SessionAccessFilter.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @Value("${gateway.ignore.startWith}")
    private String startWith;

    @Value("${gateway.ignore.contain}")
    private String contain;

    @Value("${gateway.oauth.prefix}")
    private String oauthPrefix;

    @Value("${gateway.jwt.header}")
    private String tokenHeader;

    @Value("${zuul.prefix}")
    private String zuulPrefix;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public SessionAccessFilter() {
        super();
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpSession httpSession = ctx.getRequest().getSession();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI().substring(zuulPrefix.length());
        final String method = request.getMethod();
        //不进行拦截的地址
        if (isStartWith(requestUri) || isContains(requestUri) || isOAuth(requestUri))
            return null;
        UserInfo user = getJwtUser(request);
        String username = null;
        if (user != null) {
            username = user.getUsername();
            //设置头部校验信息
            ctx.addZuulRequestHeader("Authorization", Base64Utils.encodeToString(user.getUsername().getBytes()));
        } else {
            setFailedRequest(JSON.toJSONString(new TokenForbiddenResponse("Token Forbidden")), 200);
        }
        List<PermissionInfo> permissionInfos = userService.getAllPermissionInfo();
        //判断资源是否启用权限约束
        Collection<PermissionInfo> result = getPermissionInfos(requestUri, method, permissionInfos);
        if (!result.isEmpty()) {
            if (username != null) {
                checkAllow(requestUri, method, ctx, username);
            }
        }
        return null;

    }

    /**
     * 获取目标权限资源
     *
     * @param requestUri
     * @param method
     * @param serviceInfo
     * @return
     */
    private Collection<PermissionInfo> getPermissionInfos(final String requestUri, final String method, List<PermissionInfo> serviceInfo) {
        return Collections2.filter(serviceInfo, new Predicate<PermissionInfo>() {
            @Override
            public boolean apply(PermissionInfo permissionInfo) {
                String url = permissionInfo.getUri();
                String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
                String regEx = "^" + uri + "$";
                return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"))
                        && method.equals(permissionInfo.getMethod());
            }
        });
    }


    /**
     * 读取权限
     *
     * @param request
     * @param username
     * @return
     */
    private List<PermissionInfo> getPermissionInfos(HttpServletRequest request, String username) {
        List<PermissionInfo> permissionInfos;
        if (request.getSession().getAttribute("permission") == null) {
            permissionInfos = userService.getPermissionByUsername(username);
            request.getSession().setAttribute("permission", permissionInfos);
        } else {
            permissionInfos = (List<PermissionInfo>) request.getSession().getAttribute("permission");
        }
        return permissionInfos;
    }


    /**
     * 权限校验
     *
     * @param requestUri
     * @param method
     */
    private void checkAllow(final String requestUri, final String method, RequestContext ctx, String username) {
        log.debug("uri：" + requestUri + "----method：" + method);
        List<PermissionInfo> permissionInfos = getPermissionInfos(ctx.getRequest(), username);
        Collection<PermissionInfo> result = getPermissionInfos(requestUri, method, permissionInfos);
        if (result.size() <= 0) {
            setFailedRequest(JSON.toJSONString(new TokenErrorResponse("Token Forbidden!")), 200);
        } else {
            PermissionInfo[] pms = result.toArray(new PermissionInfo[]{});
            PermissionInfo pm = pms[0];
            if (!method.equals("GET")) {
                setCurrentUserInfoAndLog(ctx, username, pm);
            }
        }
    }


    private void setCurrentUserInfoAndLog(RequestContext ctx, String username, PermissionInfo pm) {
        UserInfo info = userService.getUserByUsername(username);
        String host = ClientUtil.getClientIp(ctx.getRequest());
        ctx.addZuulRequestHeader("userId", info.getId());
        ctx.addZuulRequestHeader("userName", URLEncoder.encode(info.getName()));
        ctx.addZuulRequestHeader("userHost", host);
        LogInfo logInfo = new LogInfo(pm.getMenu(), pm.getName(), pm.getUri(), new Date(), info.getId(), info.getName(), host);
        DBLog.getInstance().setLogService(logService).offerQueue(logInfo);
    }

    /**
     * 判定是否oauth资源
     *
     * @param requestUri
     * @return
     */
    private boolean isOAuth(String requestUri) {
        return requestUri.startsWith(oauthPrefix);
    }


    /**
     * 是否包含某种特征
     *
     * @param requestUri
     * @return
     */
    private boolean isContains(String requestUri) {
        boolean flag = false;
        for (String s : contain.split(",")) {
            if (requestUri.contains(s))
                return true;
        }
        return flag;
    }

    /**
     * URI是否以什么开始
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s))
                return true;
        }
        return flag;
    }

    /**
     * 返回session中的用户信息
     *
     * @param request
     * @return
     */
    private UserInfo getJwtUser(HttpServletRequest request) {
        String authToken = request.getHeader(this.tokenHeader);
        if (authToken != null) {
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            log.info("checking authentication " + username);
            if (username != null) {
                UserInfo userDetails = userService.getUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    return userDetails;
                }
            }
            setFailedRequest(JSON.toJSONString(new TokenErrorResponse("Token Error or Token Expired!")), 200);
            return null;
        } else {
            return null;
        }
    }

    /**
     * Reports an error message given a response body and code.
     *
     * @param body
     * @param code
     */
    private void setFailedRequest(String body, int code) {
        log.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
            throw new RuntimeException("Code: " + code + ", " + body); //optional
        }
    }
}
