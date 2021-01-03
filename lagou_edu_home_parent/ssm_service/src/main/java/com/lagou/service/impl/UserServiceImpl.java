package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*
        用户分页及多条件查询
     */
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());

        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);

        return pageInfo;
    }

    /*
        用户状态设置
     */
    @Override
    public void updateUserStatus(Integer id, String status) {

        //补全信息
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        Date date = new Date();
        user.setUpdate_time(date);

        //调用mapper
        userMapper.updateUserStatus(user);
    }

    /*
        用户登录
     */
    @Override
    public User login(User user) throws Exception {

        //1.调用mapper方法 user2包含了密文密码
        User user2 = userMapper.login(user);

        if (user2 != null && Md5.verify(user.getPassword(),"lagou",user2.getPassword())) {
            return user2;
        } else {
            return null;
        }
    }

    /*
        分配角色（回显）
     */
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {

        List<Role> list = userMapper.findUserRelationRoleById(id);

        return list;
    }

    /*
        用户角色分配
     */
    @Override
    public void userContextRole(UserVo userVo) {

        //1.根据用户id清空中间表关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());

        //2.再重新建立关联关系
        for (Integer roleId : userVo.getRoleIdList()) {

            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    /*
       获取用户权限，进行菜单动态展示
       再service中直接完成对ResponseResult响应对象的封装
    */
    @Override
    public ResponseResult getUserPermission(Integer userId) {

        //1.根据用户id获取用户所拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);

        //2.获取roleList中的id值单独存在一个list中
       List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            Integer id = role.getId();
            roleIds.add(id);
        }

        //3.根据角色id值，查询父级菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        //4.查询封装父级菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        //5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //封装ResponseResult，进行返回，对照接口文档
        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }


}
