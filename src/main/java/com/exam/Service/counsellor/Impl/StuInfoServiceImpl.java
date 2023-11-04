package com.exam.Service.counsellor.Impl;

import com.exam.Service.counsellor.StuInfoService;
import com.exam.dao.counsellor.StuInfoDao;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuInfoServiceImpl implements StuInfoService {
    @Autowired
    private StuInfoDao stuInfoDao;

    @Override
    public List<Student> showStudent() {
        List<Student> students = stuInfoDao.showStudent();
        return students;
    }


    //    CM07-01
//    功能名称： 添加学生信息模块
    @Override
    public int addStudentinfo(Student student) {
        int addStudent= stuInfoDao.addStudentinfo(student);
        return addStudent;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @Override
    public int deleteStudent(List<Integer> ids) {
        int deleteStudent = stuInfoDao.deleteStudent(ids);
        return deleteStudent;
    }


    //    CM07-03
//    功能名称： 修改学生信息模块
    @Override
    public int updataStudent(Student student) {
        int updataStudent = stuInfoDao.updataStudent(student);
        return updataStudent;
    }


    //    CM07-04
//    功能名称： 查询学生信息模块
    @Override
    public List<Student> findByName(String stuName) {
        List<Student> findByname = stuInfoDao.findByName(stuName);
        return findByname;
    }



}
