package com.lagou.dao;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /*
        分页查询广告信息
     */
    List<PromotionAd> findAllPromotionAdByPage();

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
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
