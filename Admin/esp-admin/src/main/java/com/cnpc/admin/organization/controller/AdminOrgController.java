package com.cnpc.admin.organization.controller;

import com.cnpc.admin.entity.PageData;
import com.cnpc.admin.organization.service.IAdminOrgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sys/admin")
public class AdminOrgController {
    @Resource(name="adminOrgService")
    private IAdminOrgService adminOrgService;

    @RequestMapping("/adminList")
    @ResponseBody
    public String adminList() throws Exception {
        List<PageData> lists = adminOrgService.findAll();
        return "org-hello";
    }
}
