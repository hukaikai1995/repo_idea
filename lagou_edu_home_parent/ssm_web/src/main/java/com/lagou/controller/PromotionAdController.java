package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
        广告分页查询
     */
    @RequestMapping("findAllPromotionAdByPage")
    //get请求没有请求体，不需要@RequestBody，可以直接封装
    public ResponseResult findAllByPage(PromotionAdVO promotionAdVO) {

        //调用service
        PageInfo<PromotionAd> allPromotionAdByPage = promotionAdService.findAllPromotionAdByPage(promotionAdVO);

        //返回结果
        ResponseResult responseResult = new ResponseResult(true, 200, "广告分页查询成功", allPromotionAdByPage);
        return responseResult;
    }

    /*
        图片上传
     */
    /*
        课程图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断接收到的上传文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }

        //2.获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");

        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

        //3.获取原文件名
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在则创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录： " + filePath);
        }

        //图片就进行了真正的上传了
        file.transferTo(filePath);

        //6.将文件名和文件路径返回，进行响应
        HashMap<String, String> map = new HashMap<>();

        map.put("fileName", newFileName);
        //http://localhost:8080/upload/1597112871741.JPG
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);

        //7.返回响应对象
        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;
    }

    /*
        根据广告id查询广告信息
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id){

        //调用service
        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);

        //返回结果
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", promotionAdById);
        return responseResult;
    }

    /*
        添加&修改广告
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {

        //判断promotionAd中是否携带id
        if (promotionAd.getId() == null) {
            //调用service的新增方法
            promotionAdService.savePromotionAd(promotionAd);

            //返回结果
            ResponseResult responseResult = new ResponseResult(true, 200, "添加广告成功", null);
            return responseResult;
        } else{
            //调用service的修改方法
            promotionAdService.updatePromotionAd(promotionAd);

            //返回结果
            ResponseResult responseResult = new ResponseResult(true, 200, "修改广告成功", null);
            return responseResult;
        }
    }

    /*
        广告动态上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status){

        //调用service
        promotionAdService.updatePromotionAdStatus(id,status);

        //返回结果
        HashMap<Object, Object> map = new HashMap<>();

        map.put("status", status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改状态成功", map);
        return responseResult;
    }

}
