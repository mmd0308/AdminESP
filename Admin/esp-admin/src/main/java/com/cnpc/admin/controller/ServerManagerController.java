package com.cnpc.admin.controller;

import com.cnpc.admin.entity.ServerManager;
import com.cnpc.admin.service.ServerManagerService;
import com.cnpc.common.controller.BaseController;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/server")
public class ServerManagerController extends BaseController<ServerManagerService, ServerManager> {


}
