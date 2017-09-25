package com.cnpc.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-22
 */
@Controller
public class SecurityController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
