package com.exam.Service.Impl;

import com.exam.Service.StudentService;
import com.exam.dao.StudentDao;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> showStudent() {
        List<Student> deps = studentDao.showStudent();
        return deps;
    }


    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addStudentinfo(Student student) {
        int addStudent= studentDao.addStudentinfo(student);
        return addStudent;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteStudent(int id) {
        int deleteDep = studentDao.deleteStudent(id);
        return deleteDep;
    }


    //    CM07-03
//    功能名称： 修改学生信息模块
    @Override
    public int updataStudent(Student student) {
        int updataStudent = studentDao.updataStudent(student);
        return updataStudent;
    }


    //    CM07-04
//    功能名称： 查询学生信息模块
    @Override
    public List<Student> findByName(String stuName) {
        List<Student> findByname = studentDao.findByName(stuName);
        return findByname;
    }



}
