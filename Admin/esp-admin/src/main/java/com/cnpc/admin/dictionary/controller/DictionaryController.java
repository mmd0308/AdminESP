package com.cnpc.admin.dictionary.controller;

import com.cnpc.admin.basic.BaseController;
import com.cnpc.admin.dictionary.service.IDictionaryService;
import com.cnpc.admin.entity.PageData;
import com.cnpc.admin.utils.JsonJackUtil;
import com.cnpc.admin.utils.NotNUllUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sys/dictionary")
public class DictionaryController extends BaseController{
    @Resource(name="dictionaryService")
    private IDictionaryService dictionaryService;

    /**
     * 根据条件查询，条件为空，查询所有数据
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list() {
        PageData pd  = this.getPageData();
        List<PageData> lists = null;
        try {
            lists = dictionaryService.find(pd);
        } catch (Exception e) {
            pd.put("res","error");
            return JsonJackUtil.ObjectToJson(pd);
        }
        return JsonJackUtil.ObjectToJson(lists);
    }

    /**
     * 查询唯一编码
     * @return
     */
    @RequestMapping("/findOnlyCode")
    public String findOnlyCode()  {
        PageData pd = this.getPageData();
        String code = pd.getString("code");
        if(NotNUllUtil.notNull(code)){
            List<PageData> dataList = null;
            try {
                dataList = dictionaryService.find(pd);
            } catch (Exception e) {
                pd.put("res","error");
                return JsonJackUtil.ObjectToJson(pd);
            }
            if (dataList != null && dataList.size()==0){
                pd.put("res","success");
            }else {
                pd.put("res","error");
                pd.put("error","code不能唯一");
            }
        }else{
            pd.put("res","error");
            pd.put("error","code不能为空");
        }
        return  JsonJackUtil.ObjectToJson(pd);
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping("/save")
    public String save() throws Exception {
        PageData pd = this.getPageData();
        dictionaryService.save(pd);
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
        dictionaryService.update(pd);
        return null;
    }

    /**
     * 根据Id删除
     * @return
     */
    @RequestMapping("/deleted")
    public String deleted(){
        PageData pd = this.getPageData();
        try {
            dictionaryService.deletedById(pd);
        } catch (Exception e) {

        }
        pd.put("res","success");
        pd.put("success","删除成功！");
        return JsonJackUtil.ObjectToJson(pd);
    }


}
