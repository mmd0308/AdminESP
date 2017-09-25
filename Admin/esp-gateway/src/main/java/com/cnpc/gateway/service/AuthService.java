package com.cnpc.gateway.service;

import com.cnpc.gateway.vo.UserVo;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-22
 */
public interface AuthService {

    String login(String username, String secret);
    String refresh(String oldToken);
    Boolean validate(String token,String resource);
    UserVo getUserInfo(String token);
    Boolean invalid(String token);
}
