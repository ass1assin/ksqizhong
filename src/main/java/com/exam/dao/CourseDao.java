package com.exam.dao;

import com.exam.entity.Classes;
import com.exam.entity.Course;
import com.exam.entity.Department;

import java.util.List;

public interface CourseDao {
    int addCourse(Course course);

    List<Classes> showClasses();

    List<Course> showCourse();

    List<Course> findAllWithPagination(int page, int pageSize);
    int getTotalCount();
    int deleteCourse(String id);

    int updataCourse(Course course);
//    List<Course> findByName(String courseName);
    List<Course> findByName(String courseID,String term,String courseName,int page, int pageSize);
    int getTotalCountByName(String courseName);
}
