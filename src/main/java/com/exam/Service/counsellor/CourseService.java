package com.exam.Service.counsellor;

import com.exam.entity.Classes;
import com.exam.entity.Course;
import com.exam.entity.Department;

import java.util.List;

public interface CourseService {
    int addCourse(Course course);
    List<Course> showCourse();

    List<Course> getDepsWithPagination(int page, int pageSize);
    int getTotalPages(int pageSize);
    int deleteCourse(String id);
    int updataCourse(Course course);
    List<Course> findByName(String className,int page, int  pageSize);
    int getTotalPagesByName(int pageSize ,String depName);
}
