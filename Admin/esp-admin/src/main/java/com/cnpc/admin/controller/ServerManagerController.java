package com.cnpc.admin.controller;

import com.cnpc.admin.entity.ServerManager;
import com.cnpc.admin.service.ServerManagerService;
import com.cnpc.common.controller.BaseController;

import com.cnpc.common.message.ObjectRestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/server")
public class ServerManagerController extends BaseController<ServerManagerService, ServerManager> {
    @GetMapping("/getImages")
    public ObjectRestResponse<Map> getImagesRepo(String all){
        HashMap<String,Object> map = new HashMap<>();
        map.put("all",all);
        ResponseEntity<Object> imagesRepo = baseService.getImagesRepo(map);
        return new ObjectRestResponse<>().rel(true).data(imagesRepo);
    }
}
