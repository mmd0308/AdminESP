package com.cnpc.admin.log.controller;

import com.cnpc.admin.entity.PageData;
import com.cnpc.admin.log.service.ILogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sys/log")
public class LogController {
    @Resource(name="logService")
    private ILogService logService;

    @RequestMapping("/adminList")
    @ResponseBody
    public String adminList() throws Exception {
        List<PageData> lists = logService.findAll();
        return "org-hello";
    }
}
