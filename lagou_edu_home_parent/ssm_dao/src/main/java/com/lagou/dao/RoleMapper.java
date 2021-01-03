package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

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
        根据角色id获取当前角色拥有的资源信息
     */

}
