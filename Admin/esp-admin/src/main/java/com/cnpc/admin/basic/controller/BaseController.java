package com.cnpc.admin.basic.controller;

import com.cnpc.admin.entity.PageData;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class BaseController {


    /**
     * @Title: getPageData
     * @Description: new PageData对象
     * @return PageData
     * @Author:
     * @Date: 2016年5月16日 下午5:13:58
     */
    public PageData getPageData(){
        return new PageData(this.getRequest());
    }


    /**
     * @Title: getModelAndView
     * @Description: 得到ModelAndView
     * @return ModelAndView
     * @Author:
     * @Date: 2016年5月16日 下午5:14:11
     */
    public ModelAndView getModelAndView(){
        return new ModelAndView();
    }

    /**
     * @Title: getRequest
     * @Description: 得到request对象
     * @return HttpServletRequest
     * @Author:
     * @Date: 2016年5月16日 下午5:14:30
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 日志记录
     */
    public void Log(){

    }


}
