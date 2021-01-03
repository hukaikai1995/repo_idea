package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;

public interface ResourceService {

    /*
       资源分页及多条件组合查询
    */
    PageInfo<Resource> findAllResourceByPage(ResourseVo resourseVo);
}
