package com.exam.Service.counsellor.Impl;

import com.exam.Service.counsellor.ClassService;
import com.exam.Service.counsellor.CourseService;
import com.exam.dao.counsellor.ClassDao;
import com.exam.dao.counsellor.CourseDao;
import com.exam.entity.Classes;
import com.exam.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> showCourse() {
        List<Course> CourseList= courseDao.showCourse();
        return CourseList;
    }


    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addCourse(Course course) {
        int addCourse= courseDao.addCourse(course);
        return addCourse;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteCourse(List<Integer> ids) {
        int deleteClass = courseDao.deleteCourse(ids);
        return deleteClass;
    }


    //    CM07-03
//    功能名称： 修改学生信息模块
    @Override
    public int updataCourse(Course course) {
        int updataCourse = courseDao.updataCourse(course);
        return updataCourse;
    }


    //    CM07-04
//    功能名称： 查询学生信息模块
    @Override
    public List<Course> findByName(String courseName) {
        List<Course> findByname = courseDao.findByName(courseName);
        return findByname;
    }



}
