package com.cnpc.admin.controller;

import com.cnpc.admin.constant.CommonConstant;
import com.cnpc.admin.entity.Menu;
import com.cnpc.admin.service.MenuService;
import com.cnpc.admin.service.UserService;
import com.cnpc.admin.vo.MenuTree;
import com.cnpc.common.controller.BaseController;
import com.cnpc.common.util.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-10-12
 */

@RestController
@RequestMapping(value = "/menu")
public class MenuController extends BaseController<MenuService, Menu> {


    @Autowired
    private UserService userService;

    @GetMapping(value = "/list")
    @ResponseBody
    public List<Menu> list(String name){
        Example example=new Example(Menu.class);
        if(StringUtils.isNotBlank(name)){
            example.createCriteria().andLike("name","%"+name+"%");
        }
        return baseService.selectByExample(example);
    }

    @GetMapping(value = "/tree")
    @ResponseBody
    public List<MenuTree> getTree(String name) {
        Example example = new Example(Menu.class);
        if (StringUtils.isNotBlank(name))
            example.createCriteria().andLike("name", "%" + name + "%");
        example.setOrderByClause("levelcode asc");
        return getMenuTree(baseService.selectByExample(example), CommonConstant.ROOT);
    }

    @GetMapping(value = "/getNextCode")
    @ResponseBody
    public String getNextCode(String parentId){
        return baseService.getNextCode(parentId);
    }

    //判断编码的唯一性
    @GetMapping(value="/checkCode")
    @ResponseBody
    public Boolean checkCode(String code,String id){
        if(StringUtils.isBlank(code)){
            return true;
        }else if(StringUtils.isNotBlank(id)&&!id.equals(CommonConstant.ROOT)){
            //编辑时校验
            Example example =new Example(Menu.class);
            example.createCriteria().andEqualTo("code",code).andNotEqualTo("id",id);
            List<Menu> exists=baseService.selectByExample(example);
            if(exists.isEmpty())
                return true;
            else
                return false;
        }else{
            //新增时校验
           Example example =new Example(Menu.class);
           example.createCriteria().andEqualTo("code",code);
           List<Menu> exists=baseService.selectByExample(example);
           if(exists.isEmpty()){
               return true;
           }else{
               return false;
           }
        }
    }

    private List<MenuTree> getMenuTree(List<Menu> menus,String rootId){
       List<MenuTree> menuTrees=new ArrayList<>();
       MenuTree node=null;
        for (Menu menu : menus) {
            node=new MenuTree();
            BeanUtils.copyProperties(menu,node);
            node.setLabel(menu.getName());
            menuTrees.add(node);
        }
        return TreeUtil.build(menuTrees,rootId);
    }

    @GetMapping(value="/system")
    @ResponseBody
    public List<Menu> getSystem(){
        Menu menu=new Menu();
        menu.setParentId(CommonConstant.ROOT);
        return baseService.selectList(menu);
    }

    @GetMapping(value="/menuTree")
    @ResponseBody
    public List<MenuTree> listMenu(String parentId) {
        try {
            if (parentId == null) {
                parentId = this.getSystem().get(0).getId();
            }
        } catch (Exception e) {
            return new ArrayList<MenuTree>();
        }
        List<MenuTree> trees = new ArrayList<MenuTree>();
        Example example = new Example(Menu.class);
        Menu parent = (Menu) baseService.selectById(parentId);
        example.createCriteria().andLike("path", parent.getLevelcode() + "%").
                andNotEqualTo("id",parent.getId());
        return getMenuTree(baseService.selectByExample(example), parent.getId());
    }


  /*
    @RequestMapping(value = "/authorityTree", method = RequestMethod.GET)
    @ResponseBody
    public List<AuthorityMenuTree> listAuthorityMenu() {
        List<AuthorityMenuTree> trees = new ArrayList<AuthorityMenuTree>();
        AuthorityMenuTree node = null;
        for (Menu menu : baseService.selectListAll()) {
            node = new AuthorityMenuTree();
            node.setText(menu.getTitle());
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees,CommonConstant.ROOT);
    }

    @RequestMapping(value = "/user/authorityTree", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuTree> listUserAuthorityMenu(Integer parentId){
        int userId = userService.getUserByUsername(getCurrentUserName()).getId();
        try {
            if (parentId == null) {
                parentId = this.getSystem().get(0).getId();
            }
        } catch (Exception e) {
            return new ArrayList<MenuTree>();
        }
        return getMenuTree(baseService.getUserAuthorityMenuByUserId(userId),parentId);
    }

    @RequestMapping(value = "/user/system", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> listUserAuthoritySystem() {
        int userId = userService.getUserByUsername(getCurrentUserName()).getId();
        return menuService.getUserAuthoritySystemByUserId(userId);
    }
   */

}
