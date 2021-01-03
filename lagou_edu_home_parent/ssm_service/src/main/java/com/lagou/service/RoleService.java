package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {

    /*
        查询所有角色（根据条件）
     */
    List<Role> findAllRole(Role role);

    /*
      根据角色信息，查询角色关联的菜单信息id
   */
    List<Integer> findMenuByRoleId(Integer roleId);

    /*
        为角色分配菜单信息
     */
    void roleContextMenu(RoleMenuVo roleMenuVo);

    /*
        删除角色
     */
    void deleteRole(Integer roleId);

    /*
        根据角色id获取当前角色拥有的源源分类信息及资源信息
     */
    ResponseResult findResourceListByRoleId(Integer id);

    /*
        为角色分配资源
     */
    void roleContextResource(RoleResourceVo roleResourceVo);
}
