package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired //注入CourseContentService实例
    private CourseContentService courseContentService;

    /*
        根据课程id查询关联的章节信息及章节信息关联的课时信息
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId){

        //调用service
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        //返回结果
        ResponseResult responseResult = new ResponseResult(true, 200, "章节及课程查询成功", list);

        return responseResult;
    }

     /*
        回显章节对应的课程信息,根据课程id查询课程信息
     */
     @RequestMapping("/findCourseByCourseId")
     public ResponseResult findCourseByCourseId(Integer courseId){

         //调用service
         Course course = courseContentService.findCourseByCourseId(courseId);

         //返回结果
         ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", course);
         return responseResult;
     }

     /*
        新增&更新章节信息
      */
     @RequestMapping("/saveOrUpdateSection")
     public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

         //判断是否携带了章节id
         if (courseSection.getId() == null) {
             //调用service的新增方法
             courseContentService.saveSection(courseSection);

             //返回结果
             ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
             return responseResult;
         } else {
             //调用service的修改方法
             courseContentService.updateSection(courseSection);

             //返回结果
             ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
             return responseResult;
         }
     }

    /*
       修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id, Integer status) {

        //调用service
        courseContentService.updateSectionStatus(id, status);

        //返回结果
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "", map);
        return responseResult;
    }

    /*
        新建课时信息
     */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson) {

        //调用service
        courseContentService.saveLesson(courseLesson);

        //返回结果
        ResponseResult responseResult = new ResponseResult(true, 200, "新建课时成功", null);
        return responseResult;
    }
}
