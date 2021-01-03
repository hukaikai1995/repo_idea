package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

     /*
        查询所有角色（根据条件）
     */
     @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

         //调用service
         List<Role> allRole = roleService.findAllRole(role);

         //返回结果
         ResponseResult responseResult = new ResponseResult(true, 200, "查询所有角色成功", allRole);
         return responseResult;
     }

     /*
        查询所有父子菜单信息
      */
     @RequestMapping("/findAllMenu")
     public ResponseResult findSubMenuListByPid(){

         //调用service,-1表示查询所有的父子级菜单
         List<Menu> menuList = menuService.findSubMenuListByPid(-1);

         //响应数据
         HashMap<String, Object> map = new HashMap<>();
         map.put("parentMenuList",menuList);
         ResponseResult responseResult = new ResponseResult(true, 200, "查询所有的父子菜单成功", map);
         return responseResult;
     }

     /*
        根据角色id，查询关联的菜单id
      */
     @RequestMapping("/findMenuByRoleId")
     public ResponseResult findMenuByRoleId(Integer roleId){

         //调用service
         List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

         //响应数据
         ResponseResult responseResult = new ResponseResult(true, 200, "查询角色关联的菜单信息成功", menuByRoleId);

         return responseResult;
     }

     /*
        为角色分配菜单
      */
     @RequestMapping("/RoleContextMenu")
     public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){

         //调用service
         roleService.roleContextMenu(roleMenuVo);

         //响应数据
         ResponseResult responseResult = new ResponseResult(true, 200, "为角色分配菜单成功", null);

         return responseResult;
     }

     /*
        删除角色
      */
     @RequestMapping("/deleteRole")
     public ResponseResult deleteRole(Integer id){

         //调用service
         roleService.deleteRole(id);

         //响应数据
         ResponseResult responseResult = new ResponseResult(true,200,"删除角色成功",null);

         return responseResult;

     }

}
