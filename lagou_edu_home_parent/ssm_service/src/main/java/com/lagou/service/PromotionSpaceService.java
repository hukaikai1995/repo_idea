package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    /*
        查询所有广告位
     */
    List<PromotionSpace> findAllPromotionSpace();

    /*
        添加广告位
     */
    void savePromotionSpace(PromotionSpace promotionSpace);

    /*
        根据id查询广告位信息
    */
    PromotionSpace findPromotionSpaceById(Integer id);

    /*
       修改广告位
    */
    void updatePromotionSpace(PromotionSpace promotionSpace);
}
