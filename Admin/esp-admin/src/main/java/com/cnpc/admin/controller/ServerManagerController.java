package com.cnpc.admin.controller;

import com.cnpc.admin.entity.ServerManager;
import com.cnpc.admin.service.ServerManagerService;
import com.cnpc.common.controller.BaseController;
import com.cnpc.common.message.ObjectRestResponse;
import com.cnpc.common.util.JsonUtil;
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

    /**
     * 容器停止
     * @param id
     * @param sip
     * @return
     */
    @GetMapping("conStop/{id}")
    public ObjectRestResponse conStop(@PathVariable String id,String sip){
        baseService.conStop(id,sip);
        return new ObjectRestResponse().rel(true);
    }

    /**
     * 启动容器
     * @param id
     * @param sIp
     * @return
     */
    @GetMapping("conStart/{id}")
    public ObjectRestResponse conStart(@PathVariable String id,String sIp){
        baseService.conStart(id,sIp);
        return new ObjectRestResponse().rel(true);
    }


    /**
     * 删除容器
     * @param id
     * @param sIp
     * @return
     */
    @DeleteMapping("conRmove/{id}")
    public ObjectRestResponse conRmove(@PathVariable String id,String sIp){
        baseService.conRmove(id,sIp);
        return new ObjectRestResponse().rel(true);
    }


}
