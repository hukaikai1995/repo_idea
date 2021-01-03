package com.lagou.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
