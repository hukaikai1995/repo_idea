package com.lagou.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /*
        查询所有角色（根据条件）
     */
    @Override
    public List<Role> findAllRole(Role role) {

        List<Role> allRole = roleMapper.findAllRole(role);

        return allRole;
    }

    /*
        根据角色id信息，查询角色关联的菜单信息id
     */
    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {

        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);

        return menuByRoleId;

    }

    /*
        为角色分配菜单信息
     */
    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //1.清空中间表的关联关系，调用roleMapper
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2.遍历roleMenuVo中的menuIdList为角色分配菜单
        for (Integer mid : roleMenuVo.getMenuIdList()) {

            //封装数据
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            //调用roleMapper
            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    /*
        删除角色
     */
    @Override
    public void deleteRole(Integer roleId) {

        //调用roleMapper中根据roleId清空中间表的关联关系的方法
        roleMapper.deleteRoleContextMenu(roleId);

        //调用roleMapper中删除角色的方法
        roleMapper.deleteRole(roleId);
    }

    /*
        根据角色id获取当前角色拥有的源源分类信息及资源信息
     */
    @Override
    public ResponseResult findResourceListByRoleId(Integer id) {

        //调用roleMapper中的根据角色id查找资源分类方法
        List<ResourceCategory> resourceCategoryList = roleMapper.findAllResourceCategoryByRoleId(id);

        //调用roleMapper中的根据id查找资源方法
        List<Resource> resourceList = roleMapper.findAllResourceByRoleId(id);

        System.out.println(resourceList);

        //封装ResponseResult对象

        //1.遍历资源分类集合
        for (ResourceCategory resourceCategory : resourceCategoryList) {

            //2.获取每个资源分类对象的id
            Integer categoryId = resourceCategory.getId();

            //3.准备一个新的集合用来按照资源分类id归类资源
            List<Resource> newResourceList = new ArrayList<>();

            //4.遍历资源集合
            for (Resource resource : resourceList) {
                //5.判断资源分类id是否一致
                if (resource.getCategoryId() == categoryId) {
                    newResourceList.add(resource);
                }
            }

            //6.将具有相同资源分类id的资源集合放入资源分类对象中
            resourceCategory.setResourceList(newResourceList);

            System.out.println(resourceCategory);
        }

        //7.返回responseResult对象
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", resourceCategoryList);
        return responseResult;

    }

    /*
        为角色分配资源
     */
    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {

        //1.根据角色id清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleResourceVo.getRoleId());

        //2.重新建立关联关系
        //遍历资源id列表
        for (Integer resourceId : roleResourceVo.getResourceIdList()) {

            //封装数据
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());
            roleResourceRelation.setResourceId(resourceId);
            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedby("system");

            roleMapper.roleContextResource(roleResourceRelation);
        }
    }

}
