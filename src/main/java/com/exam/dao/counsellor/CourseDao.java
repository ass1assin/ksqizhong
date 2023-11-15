package com.exam.dao.counsellor;

import com.exam.entity.Classes;
import com.exam.entity.Course;
import com.exam.entity.Department;

import java.util.List;

public interface CourseDao {
    int addCourse(Course course);
    List<Course> showCourse();

    List<Course> findAllWithPagination(int page, int pageSize);
    int getTotalCount();
    int deleteCourse(List<Integer> ids);

    int updataCourse(Course course);
//    List<Course> findByName(String courseName);
    List<Course> findByName(String courseName,int page, int pageSize);
    int getTotalCountByName(String courseName);
}
