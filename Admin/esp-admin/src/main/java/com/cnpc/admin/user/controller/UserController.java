package com.cnpc.admin.user.controller;

import com.cnpc.admin.basic.BaseController;
import com.cnpc.admin.entity.PageData;
import com.cnpc.admin.user.service.IUserService;
import com.cnpc.admin.utils.JsonJackUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserController extends BaseController{
    @Resource(name="userService")
    private IUserService userService;

    /**
     * 根据条件查询，条件为空，查询所有数据
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list() throws Exception {
        PageData pd  = this.getPageData();
        PageHelper.startPage(1, 10);
        List<PageData> lists = userService.find(pd);
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
        userService.save(pd);
        return "success";
    }

    /**
     * 修改
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public String update() throws Exception {
        PageData pd = this.getPageData();
        userService.update(pd);
        return null;
    }

    /**
     * 根据Id删除
     * @return
     */
    @RequestMapping("/deleted")
    public String deleted() throws Exception {
        PageData pd = this.getPageData();
        userService.deletedById(pd);
        pd.put("res","success");
        pd.put("success","删除成功！");
        return JsonJackUtil.ObjectToJson(pd);
    }


}
