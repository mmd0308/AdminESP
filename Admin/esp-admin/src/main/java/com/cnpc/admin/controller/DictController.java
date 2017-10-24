package com.cnpc.admin.controller;

import com.cnpc.admin.entity.Dict;
import com.cnpc.admin.entity.Org;
import com.cnpc.admin.service.DictService;
import com.cnpc.admin.service.OrgService;
import com.cnpc.common.controller.BaseController;
import com.cnpc.common.message.ObjectRestResponse;
import com.cnpc.common.util.JsonUtil;
import com.cnpc.common.util.PinYingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/dict")
public class DictController extends BaseController<DictService, Dict> {


    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Dict> add(@RequestBody Dict org){
      /*  String spell = PinYingUtil.convertHanzi2Pinyin(org.getName(), true);
        String inital = PinYingUtil.convertHanzi2Pinyin(org.getName(),false);
        org.setLspell(spell);
        org.setUspell(spell.toUpperCase());
        org.setInitials(inital+"_"+inital.toUpperCase());*/
        baseService.insertSelective(org);
        return new ObjectRestResponse<Org>().rel(true);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<Dict> update(@RequestBody Dict org){
       /* String spell = PinYingUtil.convertHanzi2Pinyin(org.getName(), true);
        String inital = PinYingUtil.convertHanzi2Pinyin(org.getName(),false);
        org.setLspell(spell);
        org.setUspell(spell.toUpperCase());
        org.setInitials(inital+"_"+inital.toUpperCase());*/
        baseService.updateSelectiveById(org);
        return new ObjectRestResponse<Org>().rel(true);
    }

    @GetMapping("dictTree")
    public ObjectRestResponse orgTree(Dict org){
        List<Map> orgs = baseService.getTree(org);

        String str = JsonUtil.ObjectToJson(orgs).replaceAll("name","label");
        return new ObjectRestResponse<String>().rel(true).data(str);
    }

    @GetMapping(value = "/getNextCode")
    public ObjectRestResponse getNextCode(String parentId,String levelCode){
        String res = baseService.getNextLevelCode(parentId,levelCode);
        return new ObjectRestResponse<String>().rel(true).data(res);
    }

    @GetMapping(value = "/checkCode")
    public Boolean checkCode(String id , String code){
        Boolean res = baseService.checkCode(id,code);
        return res;
    }

    @RequestMapping(value = "/deleted",method = RequestMethod.DELETE)
    public Boolean deleted(String id){
        Boolean res = baseService.deleteById(id);
        return res;
    }

}
