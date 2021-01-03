package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {

    /*
        查询所有角色&条件查询,为了扩展性，用role实体来做接收参数
     */
    List<Role> findAllRole(Role role);

    /*
        根据角色信息，查询角色关联的菜单信息id
     */
    List<Integer> findMenuByRoleId(Integer roleId);

    /*
        根据roleId清空中间表的关联关系
     */
    void deleteRoleContextMenu(Integer rid);

    /*
        为角色分配菜单信息
     */
    void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
        删除角色
     */
    void deleteRole(Integer id);

    /*
        添加角色
     */

    /*
        修改角色
     */

    /*
        查询当前角色id查询该角色所拥有的资源分类信息
     */
    List<ResourceCategory> findAllResourceCategoryByRoleId(Integer id);

    /*
        根据角色id获取当前角色拥有的资源信息
     */
    List<Resource> findAllResourceByRoleId(Integer id);

    /*
        根据角色id清空 角色资源的中间表
     */
    void deleteRoleContextResource(Integer roleId);

    /*
        为角色分配资源，向角色资源的中间表添加记录
     */
    void roleContextResource(RoleResourceRelation roleResourceRelation);


}
