package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /*
        添加资源分类
     */
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {

        //1.补全信息
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setUpdatedBy("system");

        //2.调用mapper
        resourceCategoryMapper.saveResourceCategory(resourceCategory);

    }

    /*
        修改资源分类
     */
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {

        //1.补全信息
        Date date = new Date();
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setUpdatedBy("system");

        //2.调用mapper
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    /*
        删除资源分类
     */
    @Override
    public void deleteResourceCategory(Integer id) {

        //调用mapper
        resourceCategoryMapper.deleteResourceCategory(id);
    }
}
