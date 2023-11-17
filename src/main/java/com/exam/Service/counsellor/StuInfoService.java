package com.exam.Service.counsellor;

import com.exam.entity.Classes;
import com.exam.entity.Department;
import com.exam.entity.Student;

import java.util.List;

public interface StuInfoService {
    int addStudentinfo(Student student);
    List<Classes> showClasses();

    List<Student> getDepsWithPagination(int page, int pageSize);
    int getTotalPages(int pageSize);
    int deleteStudent(String id);
    int updataStudent(Student student);
    List<Student> findByName(String studentID,String stuName,int page, int  pageSize);
    int getTotalPagesByName(int pageSize ,String depName);

}
