package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
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
import java.util.List;

@RestController //组合注解，相当于@Controller和@ResponseBody
@RequestMapping("/course") //请求映射路径
public class CourseController {

    @Autowired //注入CourseService实例
    private CourseService courseService;

    //@RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
    //GET方式无请求体，所以使用@RequestBody接收数据时，前端不能使用GET方式提交数据，而是用POST方式进行提交。
    /*
        多条件课程列表查询
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){

        //System.out.println(courseVO);

        //调用service
        List<Course> list = courseService.findCourseByCondition(courseVO);

        //响应数据给前台
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);

        return responseResult;
    }

    /*
        课程图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断接收到的上传文件是否为空
        if (file.isEmpty()){
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

        map.put("fileName",newFileName);
        //http://localhost:8080/upload/1597112871741.JPG
        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        //7.返回响应对象
        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;
    }

    /*
        新增课程信息及讲师信息
        新增课程信息和修改课程信息写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO){

        //判断courseVO的id信息是否为空，为空是新增，不为空是修改
        if (courseVO.getId() == null) {
            //调用service,执行新增操作
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else {
            //调用service,执行修改操作
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }

     /*
        根据课程id查询课程信息及其关联的讲师信息
     */
     @RequestMapping("/findCourseById")
     public ResponseResult findCourseById(Integer id){

         CourseVO courseVO = courseService.findCourseById(id);

         ResponseResult responseResult = new ResponseResult(true, 200, "根据id查询课程信息成功", courseVO);

         return responseResult;
     }

    /*
       课程状态管理
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id, Integer status) {

        //调用service，传递参数，完成课程状态的变更
        courseService.updateCourseStatus(id,status);

        //响应数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态变更成功", map);

        return responseResult;
    }

}
