package com.cnpc.admin.controller;

import com.cnpc.common.controller.BaseController;
import com.cnpc.admin.entity.Org;
import com.cnpc.admin.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
