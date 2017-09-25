package com.cnpc.gateway.security;

import java.io.Serializable;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-21
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID=1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token=token;
    }

    public String getToken() {
        return token;
    }
}
