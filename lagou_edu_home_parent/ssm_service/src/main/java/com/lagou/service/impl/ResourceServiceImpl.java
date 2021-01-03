package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;


    /*
       资源分页及多条件组合查询
    */
    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourseVo resourseVo) {

        //分页查询
        PageHelper.startPage(resourseVo.getCurrentPage(),resourseVo.getPageSize());

        //调用mapper
        List<Resource> allResourceByPage = resourceMapper.findAllResourceByPage(resourseVo);

        PageInfo<Resource> resourcePageInfo = new PageInfo<>(allResourceByPage);

        return resourcePageInfo;
    }
}
