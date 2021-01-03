package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired //注入mapper代理对象
    private CourseContentMapper courseContentMapper;

    /*
       根据课程id查询关联的章节信息及章节信息关联的课时信息
    */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return list;
    }

    /*
        回显章节对应的课程信息,根据课程id查询课程信息
     */
    @Override
    public Course findCourseByCourseId(Integer courseId) {

        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    /*
      新增章节信息
   */
    @Override
    public void saveSection(CourseSection courseSection) {

        //补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        //调用mapper方法
        courseContentMapper.saveSection(courseSection);
    }

    /*
      更新章节信息
   */
    @Override
    public void updateSection(CourseSection courseSection) {

        //补全信息
        Date date = new Date();
        courseSection.setUpdateTime(date);

        //调用courseContentMapper的更新方法
        courseContentMapper.updateSection(courseSection);
    }

    /*
        修改章节状态
     */
    @Override
    public void updateSectionStatus(int id, int status) {

        //封装数据
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        courseSection.setId(id);
        courseSection.setUpdateTime(new Date());

        //调用mapper
        courseContentMapper.updateSectionStatus(courseSection);
    }

    /*
       新建课时信息
    */
    @Override
    public void saveLesson(CourseLesson courseLesson) {

        //补全信息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        //调用mapper
        courseContentMapper.saveLesson(courseLesson);
    }

}
