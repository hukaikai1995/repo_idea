package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    /*
       分页查询广告信息
    */
    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {

        //调用PageHelper的startPage方法
        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());
        //调用mapper方法
        List<PromotionAd> allPromotionAdByPage = promotionAdMapper.findAllPromotionAdByPage();

        //借助PageInfo
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(allPromotionAdByPage);

        //allPromotionAdByPage已经是经过分页查询后的数据封装了
        return pageInfo;
    }

    /*
      根据广告id查询广告信息
   */
    @Override
    public PromotionAd findPromotionAdById(Integer id) {

        //调用mapper
        PromotionAd promotionAd = promotionAdMapper.findPromotionAdById(id);
        //返回查询结果
        return promotionAd;
    }

    /*
        添加广告
     */
    @Override
    public void savePromotionAd(PromotionAd promotionAd) {

        //补全信息
        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        //调用mapper
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    /*
        修改广告
     */
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        //补全信息
        Date date = new Date();
        promotionAd.setUpdateTime(date);

        //调用mapper
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    /*
        封装数据
     */
    @Override
    public void updatePromotionAdStatus(int id, int status) {

        //1.封装数据
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        //调用mapper
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
