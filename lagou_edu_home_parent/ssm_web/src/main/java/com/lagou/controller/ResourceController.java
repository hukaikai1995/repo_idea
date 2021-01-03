package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

     /*
       资源分页及多条件组合查询
    */
     @RequestMapping("/findAllResource")
     public ResponseResult findAllResourceByPage(@RequestBody ResourseVo resourseVo){

         //调用service
         PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourseVo);

         //响应数据
         ResponseResult responseResult = new ResponseResult(true, 200, "资源分页及多条件组合查询成功", pageInfo);
         return responseResult;
     }
}
