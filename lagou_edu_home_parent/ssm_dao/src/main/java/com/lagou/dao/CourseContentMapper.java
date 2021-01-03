package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /*
        根据课程id查询关联的章节信息及章节信息关联的课时信息
     */
    List<CourseSection> findSectionAndLessonByCourseId(Integer id);

    /*
        回显章节对应的课程信息
     */
    Course findCourseByCourseId(Integer courseId);

    /*
        新增章节信息
     */
    void saveSection(CourseSection courseSection);

    /*
        更新章节信息
     */
    void updateSection(CourseSection courseSection);

    /*
        修改章节状态
     */
    void updateSectionStatus(CourseSection courseSection);

    /*
        新建课时信息
     */
    void saveLesson(CourseLesson courseLesson);
}
