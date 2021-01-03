package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
     public ResponseResult findAllResourceCategory(){

         //调用service
         List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();

         //响应数据
         ResponseResult responseResult = new ResponseResult(true, 200, "查询所有资源分类成功", allResourceCategory);
         return responseResult;

     }
}
