package com.cnpc.admin.controller;

import com.cnpc.admin.entity.Element;
import com.cnpc.admin.service.ElementService;
import com.cnpc.common.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-10-16
 * @description 菜单关联资源
 */
@RestController
@RequestMapping(value = "/element")
public class ElementController extends BaseController<ElementService,Element> {

}
