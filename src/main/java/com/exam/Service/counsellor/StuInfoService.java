package com.exam.Service.counsellor;

import com.exam.entity.Student;

import java.util.List;

public interface StuInfoService {
    int addStudentinfo(Student student);
    List<Student> showStudent();

    int deleteStudent(int id);
    int updataStudent(Student student);
    List<Student> findByName(String stuName);
}
