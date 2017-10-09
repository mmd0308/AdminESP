package com.cnpc.gateway.controller;

import com.cnpc.common.constant.RestCodeConstants;
import com.cnpc.common.constant.UserConstant;
import com.cnpc.gateway.security.JwtAuthenticationRequest;
import com.cnpc.gateway.security.JwtAuthenticationResponse;
import com.cnpc.gateway.service.AuthService;
import com.cnpc.gateway.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-22
 */
@RestController
@RequestMapping(value = "/jwt")
@RefreshScope
public class AuthController {

    @Value("${gateway.jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "hello")
    @ResponseBody
    public String sayHello(){
        return "hello world";
    }

    @RequestMapping(value = "token",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest){
        final String token=authService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword());
        if(StringUtils.isEmpty(token)){
            //密码错误
            return ResponseEntity.ok(new JwtAuthenticationResponse(false,"密码错误", RestCodeConstants.USER_PASSWORD_ERROR));
        }else if(token.equals(UserConstant.USER_NOT_EXIST)){
            //登录名错误
            return ResponseEntity.ok(new JwtAuthenticationResponse(false,"登录名不存在", RestCodeConstants.USER_NOT_EXIST));
        }
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }


    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public ResponseEntity<?> verify(String token,String resource){
        if(authService.validate(token,resource))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.status(401).body(false);
    }

    @RequestMapping(value = "invalid", method = RequestMethod.POST)
    public ResponseEntity<?> invalid(@RequestHeader("access-token") String token){
        authService.invalid(token);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo(String token){
        UserVo userInfo = authService.getUserInfo(token);
        if(userInfo==null)
            return ResponseEntity.status(401).body(false);
        else
            return ResponseEntity.ok(userInfo);
    }

}
