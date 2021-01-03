package com.lagou.service;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {

    /*
       查询所有父子菜单信息
    */
    List<Menu> findSubMenuListByPid(Integer pid);

    /*
       查询所有菜单信息，不区分父子级别,就是简单的单表查询所有
    */
    List<Menu> findAllMenu();

    /*
        根据id查询menu信息
     */
    Menu findMenuById(Integer id);
}
