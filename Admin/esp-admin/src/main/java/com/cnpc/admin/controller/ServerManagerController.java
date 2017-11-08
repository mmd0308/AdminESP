package com.cnpc.admin.controller;

import com.cnpc.admin.entity.ServerManager;
import com.cnpc.admin.service.ServerManagerService;
import com.cnpc.common.controller.BaseController;

import com.cnpc.common.message.ObjectRestResponse;
import com.cnpc.common.util.JsonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/server")
public class ServerManagerController extends BaseController<ServerManagerService, ServerManager> {
    @GetMapping("/getImages")
    public ObjectRestResponse<Map> getImagesRepo(){
        HashMap<String,Object> map = new HashMap<>();
        return new ObjectRestResponse<>().rel(true).data( baseService.getImagesRepo(map));
    }

    @GetMapping("getConByImageId")
    public ObjectRestResponse getConByImageId(String imgId){
        HashMap<String ,Object> map = new HashMap<>();
        String [] arr ={imgId};
        map.put("ancestor",arr);
        map.put("filters",JsonUtil.ObjectToJson(map));
        return new ObjectRestResponse<>().rel(true).data(baseService.getConByImageId(imgId));


    }
}
