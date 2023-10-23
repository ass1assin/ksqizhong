package com.exam.dao;

import com.exam.entity.Dep;
import com.exam.entity.Student;

import java.util.List;

public interface StudentDao {
    int addStudentinfo(Student student);
    List<Student> showStudent();
    int deleteStudent(int id);
    int updataStudent(Student student);
    List<Student> findByName(String stuName);
}
