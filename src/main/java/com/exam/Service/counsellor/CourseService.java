package com.exam.Service.counsellor;

import com.exam.entity.Classes;
import com.exam.entity.Course;

import java.util.List;

public interface CourseService {
    int addCourse(Course course);
    List<Course> showCourse();

    int deleteCourse(List<Integer> ids);
    int updataCourse(Course course);
    List<Course> findByName(String className);
}
