package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /*
        查询所有父子菜单信息
     */
    @Override
    public List<Menu> findSubMenuListByPid(Integer pid) {

        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);

        return subMenuListByPid;
    }

    /*
      查询所有菜单信息，不区分父子级别,就是简单的单表查询所有
   */
    @Override
    public List<Menu> findAllMenu() {

        List<Menu> allMenu = menuMapper.findAllMenu();

        return allMenu;
    }

    /*
        根据id查询menu信息
     */
    @Override
    public Menu findMenuById(Integer id) {

        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }
}
