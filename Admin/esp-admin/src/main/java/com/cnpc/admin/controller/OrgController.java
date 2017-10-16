package com.cnpc.admin.controller;

import com.cnpc.common.controller.BaseController;
import com.cnpc.admin.entity.Org;
import com.cnpc.admin.service.OrgService;
import com.cnpc.common.message.ObjectRestResponse;
import com.cnpc.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/org")
public class OrgController extends BaseController<OrgService, Org> {


    @GetMapping("orgTree")
    public ObjectRestResponse orgTree(Org org,String callback){
        List<Map> orgs = baseService.getTree(org);
        String str = JsonUtil.ObjectToJson(orgs).replaceAll("name","label");
        return new ObjectRestResponse<String>().rel(true).data(str);
    }

}
