package com.cnpc.admin.controller;

import com.cnpc.admin.entity.Element;
import com.cnpc.admin.entity.Menu;
import com.cnpc.admin.service.ElementService;
import com.cnpc.admin.service.MenuService;
import com.cnpc.common.constant.UserConstant;
import com.cnpc.common.controller.BaseController;
import com.cnpc.common.message.TableResultResponse;
import com.cnpc.common.util.Query;
import com.cnpc.common.vo.PermissionInfo;
import com.cnpc.common.vo.UserInfo;
import com.cnpc.admin.entity.User;
import com.cnpc.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-26
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserService, User> {

    @Autowired
    private MenuService menuService;

    @Autowired
    private ElementService elementService;

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public UserInfo getUserByUsername(@PathVariable("username") String username) {
        UserInfo info = new UserInfo();
        User user = baseService.getUserByUsername(username);
        if (user == null) {
            info.setId(UserConstant.USER_NOT_EXIST);
            return info;
        }
        BeanUtils.copyProperties(user, info);
        info.setId(user.getId().toString());
        return info;
    }


    @GetMapping(value = "/{username}/permissions")
    @ResponseBody
    List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username) {
        User user = baseService.getUserByUsername(username);
        List<Menu> menus = menuService.getMapper().selectMenusByUserId(user.getId());
        List<PermissionInfo> permissionInfos = new ArrayList<>();
        menuService.menuToPermissionInfo(menus, permissionInfos);
        List<Element> elements = elementService.getMapper().selectElementsByUserId(user.getId());
        elementService.elementToPermissionInfo(elements, permissionInfos);
        return permissionInfos;
    }

    @GetMapping(value = "/permissions")
    @ResponseBody
    List<PermissionInfo> getAllPermissionInfo() {
        List<PermissionInfo> permissionInfos = new ArrayList<>();
        Example example=new Example(Menu.class);
        example.createCriteria().andEqualTo("enabled","1");
        List<Menu> menus = menuService.selectByExample(example);
        menuService.menuToPermissionInfo(menus, permissionInfos);

        List<Element> elements = elementService.selectListAll();
        elementService.elementToPermissionInfo(elements, permissionInfos);
        return permissionInfos;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<User> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return baseService.selectByName(query);
    }


}
