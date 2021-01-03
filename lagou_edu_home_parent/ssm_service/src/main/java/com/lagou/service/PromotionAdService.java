package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

public interface PromotionAdService {

    /*
       分页查询广告信息
    */
    PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

    /*
       根据广告id查询广告信息
    */
    PromotionAd findPromotionAdById(Integer id);

    /*
        添加广告
     */
    void savePromotionAd(PromotionAd promotionAd);

    /*
        修改广告
     */
    void updatePromotionAd(PromotionAd promotionAd);

    /*
        广告动态上下线
     */
    void updatePromotionAdStatus(int id,int status);
}
