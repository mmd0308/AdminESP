package com.cnpc.ucenter.controller;

import com.cnpc.common.constant.UserConstant;
import com.cnpc.common.controller.BaseController;
import com.cnpc.common.vo.PermissionInfo;
import com.cnpc.common.vo.UserInfo;
import com.cnpc.ucenter.entity.Org;
import com.cnpc.ucenter.entity.User;
import com.cnpc.ucenter.service.OrgService;
import com.cnpc.ucenter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/org")
public class OrgController extends BaseController<OrgService, Org> {

    @Autowired
    private OrgService orgService;

    @GetMapping("orgTree")
    public String orgTree(Org org){
        List<Org> orgs = orgService.getTree(org);

        return "hello   "+orgs.toString();
    }

}
