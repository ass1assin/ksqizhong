package com.exam.dao.counsellor;

import com.exam.entity.Classes;
import com.exam.entity.Course;

import java.util.List;

public interface CourseDao {
    int addCourse(Course course);
    List<Course> showCourse();
    int deleteCourse(List<Integer> ids);
    int updataCourse(Course course);
    List<Course> findByName(String courseName);
}
