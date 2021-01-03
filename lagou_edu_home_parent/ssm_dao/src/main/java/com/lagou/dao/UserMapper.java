package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    /*
        用户分页及多条件组合查询
     */
    List<User> findAllUserByPage(UserVo userVo);

    /*
        用户状态修改
     */
    void updateUserStatus(User user);

    /*
        用户登录(根据用户名查询具体的用户信息）
     */
    User login(User user);



    /*
        根据用户id清空中间表
     */
    void deleteUserContextRole(Integer userId);

    /*
        分配角色，向中间表添加记录
     */
    void userContextRole(User_Role_relation user_role_relation);

    /*
       1.根据用户id查询关联的角色信息
    */
    List<Role> findUserRelationRoleById(Integer id);

    /*
        2.根据角色id，查询角色所拥有的顶级菜单信息
        前面1.中 根据用户id查询关联的角色信息，查询出来可能会有多个角色id
     */
    List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
        3.根据父级菜单的pid，查询子菜单信息
     */
    List<Menu> findSubMenuByPid(Integer pid);

    /*
        4.根据角色id集合，查询角色所拥有的资源信息，
          查询用户拥有的资源信息
     */
    List<Resource> findResourceByRoleId(List<Integer> ids);

}
