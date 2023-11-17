package com.exam.Service.Impl;

import com.exam.Service.CourseService;
import com.exam.dao.CourseDao;
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
    public List<Classes> showClasses() {
        List<Classes> ClassesList= courseDao.showClasses();
        return ClassesList;
    }

    @Override
    public List<Course> showCourse() {
        List<Course> CourseList= courseDao.showCourse();
        return CourseList;
    }

    @Override
    public List<Course> getDepsWithPagination(int page, int pageSize) {
        return courseDao.findAllWithPagination(page,pageSize);
    }
    public int getTotalPages(int pageSize) {
        int totalCount = courseDao.getTotalCount();
        int pageSum = (totalCount / pageSize )+1;
        return pageSum;
    }


    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addCourse(Course course) {
        course.setTerm("1");
        course.setHour(80);
        int addCourse= courseDao.addCourse(course);

        return addCourse;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteCourse(String id) {
        int deleteClass = courseDao.deleteCourse(id);
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
//    @Override
//    public List<Course> findByName(String courseName) {
//        List<Course> findByname = courseDao.findByName(courseName);
//        return findByname;
//    }
    @Override
    public List<Course> findByName(String courseID,String term,String courseName, int page, int pageSize) {
        List<Course> findByname = courseDao.findByName(courseID,term,courseName,page,pageSize);
        return findByname;
    }

    @Override
    public int getTotalPagesByName(int pageSize,String Name) {
        int totalCount = courseDao.getTotalCountByName(Name);
        int pageSum = (totalCount / pageSize )+1;
        return pageSum;
    }



}
