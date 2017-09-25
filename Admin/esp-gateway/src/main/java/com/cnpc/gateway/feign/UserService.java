package com.cnpc.gateway.feign;

import com.cnpc.common.vo.PermissionInfo;
import com.cnpc.common.vo.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-21
 */
@FeignClient(value = "admin")
@RequestMapping("/api")
public interface UserService {
    @GetMapping(value = "/user/username/{username}")
    UserInfo getUserByUsername(@PathVariable("username") String username);

    @GetMapping(value = "/user/un/{username}/permissions")
    List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username);

    @GetMapping(value = "/permissions")
    List<PermissionInfo> getAllPermissionInfo();
}
