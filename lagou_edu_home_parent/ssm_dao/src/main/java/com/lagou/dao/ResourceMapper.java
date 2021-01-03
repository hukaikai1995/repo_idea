package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;

import java.util.List;

public interface ResourceMapper {

    /*
        资源分页及多条件组合查询
     */
    List<Resource> findAllResourceByPage(ResourseVo resourseVo);

    /*
        添加资源信息
     */

    /*
        更新资源信息
     */

    /*
        删除资源信息
     */
}
