package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class promotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /*
        查询所有广告位
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){

        //调用service
        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();

        //返回结果
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);

        return responseResult;
    }

    /*
        添加广告位
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){

        //判断接收到promotionSpace对象中的id属性有没有值
        if (promotionSpace.getId() == null){
            //调用service的添加方法
            promotionSpaceService.savePromotionSpace(promotionSpace);

            //返回结果
            ResponseResult responseResult = new ResponseResult(true, 200, "新增广告位成功", null);
            return responseResult;
        } else{
            //调用service的修改方法
            promotionSpaceService.updatePromotionSpace(promotionSpace);

            //返回结果
            ResponseResult responseResult = new ResponseResult(true, 200, "修改广告位成功", null);
            return responseResult;
        }
    }

    /*
        根据id查询广告位
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(Integer id){

        //调用service
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        //返回结果
        ResponseResult responseResult = new ResponseResult(true, 200, "查询具体广告位成功", promotionSpace);
        return responseResult;

    }
}
