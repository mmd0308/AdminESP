package com.cnpc.admin.log.controller;

import com.cnpc.admin.basic.BaseController;
import com.cnpc.admin.entity.PageData;
import com.cnpc.admin.log.service.ILogService;
import com.cnpc.admin.utils.JsonJackUtil;
import com.cnpc.admin.utils.NotNUllUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/log")
public class LogController extends BaseController{
    @Resource(name="logService")
    private ILogService logService;

    /**
     * 根据条件查询，条件为空，查询所有数据
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list() throws Exception {
        PageData pd  = this.getPageData();
        PageHelper.startPage(1, 10);
        List<PageData> lists = logService.find(pd);
        PageInfo pageInfo = new PageInfo(lists);
        return JsonJackUtil.ObjectToJson(pageInfo);
    }
    /**
     * 新增
     * @return
     */
    @RequestMapping("/save")
    public String save() throws Exception {
        PageData pd = this.getPageData();
        logService.save(pd);
        return null;
    }



}
