package com.cnpc.admin.org.controller;

import com.cnpc.admin.basic.controller.BaseController;
import com.cnpc.admin.entity.PageData;
import com.cnpc.admin.org.service.OrgService;
import com.cnpc.admin.utils.JsonJackUtil;
import com.cnpc.admin.utils.NotNUllUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/org")
public class OrgController extends BaseController{
    @Resource(name="orgService")
    private OrgService orgService;

    /**
     * 根据条件查询，条件为空，查询所有数据
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list() throws Exception {
        PageData pd  = this.getPageData();
        PageHelper.startPage(1, 10);
        PageInfo pageInfo = new PageInfo(orgService.find(pd));
        return JsonJackUtil.ObjectToJson(pageInfo);
    }

    /**
     * 查询唯一编码
     * @return
     */
    @RequestMapping("/findOnlyCode")
    public String findOnlyCode() throws Exception {
        PageData pd = this.getPageData();
        String code = pd.getString("code");
        PageData res = new PageData();
        res = resError(res);
        if(NotNUllUtil.notNull(code)){
            PageHelper.startPage(1, 10);
            List<PageData> dataList = orgService.findOnlyCode(pd);
            PageInfo pageInfo  = new PageInfo(dataList);
            if (dataList != null && dataList.size()==0){
                res.clear();
                res = resError(res);
                res.put("pageInfo",pageInfo);
            }
        }
        return  JsonJackUtil.ObjectToJson(res);
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping("/save")
    public String save() throws Exception {
        PageData pd = this.getPageData();
        orgService.save(pd);
        return null;
    }

    /**
     * 修改
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public String update() throws Exception {
        PageData pd = this.getPageData();
        orgService.update(pd);
        return null;
    }

    /**
     * 根据Id删除
     * @return
     */
    @RequestMapping("/deleted")
    public String deleted() throws Exception {
        PageData pd = this.getPageData();
        orgService.deletedById(pd);
        pd = resSuccess(pd);
        return JsonJackUtil.ObjectToJson(pd);
    }
}
