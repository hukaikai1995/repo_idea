package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;
    /*
     查询所有资源分类
  */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {

        //调用mapper
        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();

        return allResourceCategory;
    }
}
