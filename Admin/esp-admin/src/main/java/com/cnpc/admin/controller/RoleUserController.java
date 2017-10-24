package com.cnpc.admin.controller;

import com.cnpc.admin.entity.User;
import com.cnpc.admin.service.RoleService;
import com.cnpc.admin.service.UserService;
import com.cnpc.common.controller.BaseController;
import com.cnpc.common.message.ObjectRestResponse;
import com.cnpc.common.message.TableResultResponse;
import com.cnpc.common.util.Query;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-10-17
 */
@RestController
@RequestMapping(value = "/role/user")
public class RoleUserController extends BaseController<UserService, User> {

    @Autowired
    private RoleService roleService;

    /**
     * 关联角色的用户列表
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<User> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return baseService.selectUsersWithRoleId(query);
    }

    /**
     * 待选择的用户列表（未关联角色)
     */
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<User> select(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return baseService.selectUsersWithoutRoleId(query);
    }


    @RequestMapping(value = "/{roleId}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<Object> deleteRoleUser(@PathVariable String roleId, String userIds) {
        roleService.deleteRoleUser(roleId,userIds);
        return new ObjectRestResponse<>().rel(true);
    }


    @RequestMapping(value = "/{roleId}/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Object> addRoleUsers(@PathVariable String roleId, String userIds) {
        roleService.insertRoleUsers(roleId, userIds);
        return new ObjectRestResponse<>().rel(true);
    }
}
