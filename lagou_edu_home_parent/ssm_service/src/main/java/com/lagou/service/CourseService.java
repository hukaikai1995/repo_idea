package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.util.List;

public interface CourseService {

    /*
        多条件课程列表查询
     */
    List<Course> findCourseByCondition(CourseVO courseVO);

    /*
        添加课程及讲师信息
     */
    void saveCourseOrTeacher(CourseVO courseVO);

    /*
        根据课程id查询课程信息及其关联的讲师信息
     */
    CourseVO findCourseById(Integer id);

    /*
        更新课程及讲时信息
     */
    void updateCourseOrTeacher(CourseVO courseVO);

    /*
       课程状态管理
    */
    void updateCourseStatus(int courseId, int status);



}
