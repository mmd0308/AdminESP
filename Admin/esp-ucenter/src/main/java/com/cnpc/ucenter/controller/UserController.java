package com.cnpc.ucenter.controller;

import com.cnpc.common.constant.UserConstant;
import com.cnpc.common.controller.BaseController;
import com.cnpc.common.vo.PermissionInfo;
import com.cnpc.common.vo.UserInfo;
import com.cnpc.ucenter.entity.User;
import com.cnpc.ucenter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-26
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserService, User> {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public UserInfo getUserByUsername(@PathVariable("username") String username) {
        UserInfo info = new UserInfo();
        User user = userService.getUserByUsername(username);
        if(user==null){
            info.setId(UserConstant.USER_NOT_EXIST);
            return info;
        }
        BeanUtils.copyProperties(user, info);
        info.setId(user.getId().toString());
        return info;
    }


    @GetMapping(value = "/{username}/permissions")
    @ResponseBody
    List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username){
        return new ArrayList<>();
    }

    @GetMapping(value = "/permissions")
    @ResponseBody
    List<PermissionInfo> getAllPermissionInfo(){
        return new ArrayList<>();
    }


}
