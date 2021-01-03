package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {

    /*
        用户分页及多条件查询
     */
    PageInfo findAllUserByPage(UserVo userVo);

    /*
        用户状态修改
     */
    void updateUserStatus(Integer id, String status);

    /*
        用户登录
     */
    User login(User user) throws Exception;

    /*
        分配角色（回显）
     */
    List<Role> findUserRelationRoleById(Integer id);

    /*
        用户关联角色
     */
    void userContextRole(UserVo userVo);

    /*
        获取用户权限，进行菜单动态展示
        再service中直接完成对ResponseResult响应对象的封装
     */
    ResponseResult getUserPermission(Integer userId);

}
