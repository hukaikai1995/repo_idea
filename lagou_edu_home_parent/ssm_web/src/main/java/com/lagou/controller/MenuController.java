package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
      查询所有菜单信息，不区分父子级别,就是简单的单表查询所有
   */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {

        //调用service
        List<Menu> allMenu = menuService.findAllMenu();

        //响应数据
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有菜单信息成功", allMenu);
        return responseResult;
    }

    /*
       回显菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id) {

        //根据id的值判断当前是更新还是修改操作,id=-1为添加，id不等于-1为修改
        if (id == -1) {
            //添加操作，回显信息中不需要查询menu信息 调用service
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //查看接口文档的返回格式，需要先封装数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", subMenuListByPid);
            //响应数据
            ResponseResult responseResult = new ResponseResult(true, 200, "添加回显成功", map);
            return responseResult;
        } else {

            //修改操作 回显信息中需要查询menu信息 调用service
            //根据id查询菜单信息的方法
            Menu menu = menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //查看接口文档的返回格式，需要先封装数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", subMenuListByPid);

            //响应数据
            ResponseResult responseResult = new ResponseResult(true, 200, "修改回显成功", map);
            return responseResult;
        }
    }
}
