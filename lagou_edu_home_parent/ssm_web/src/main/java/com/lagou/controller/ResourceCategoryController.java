package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /*
       查询所有资源分类
    */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {

        //调用service
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();

        //响应数据
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有资源分类成功", allResourceCategory);
        return responseResult;
    }

    /*
        添加&修改资源分类
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory) {

        //判断resourceCategory对象中是否携带id值
        if (resourceCategory.getId() == null) {

            //调用service，新增操作
            resourceCategoryService.saveResourceCategory(resourceCategory);

            //响应数据
            ResponseResult responseResult = new ResponseResult(true, 200, "添加资源分类成功", null);
            return responseResult;
        } else {

            //调用service，修改操作
            resourceCategoryService.updateResourceCategory(resourceCategory);

            //响应数据
            ResponseResult responseResult = new ResponseResult(true, 200, "修改资源分类成功", null);
            return responseResult;
        }
    }

    /*
        删除资源分类
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){

        //调用service
        resourceCategoryService.deleteResourceCategory(id);

        //响应数据
        ResponseResult responseResult = new ResponseResult(true, 200, "删除资源分类成功", null);
        return responseResult;
    }
}
