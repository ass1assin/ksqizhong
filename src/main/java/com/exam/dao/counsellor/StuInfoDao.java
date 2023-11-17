package com.exam.dao.counsellor;

import com.exam.entity.Classes;
import com.exam.entity.Department;
import com.exam.entity.Student;

import java.util.List;

public interface StuInfoDao {
    int addStudentinfo(Student student);
//    List<Student> showStudent();
    //显示所有信息
    List<Classes> showClass();

    List<Student> findAllWithPagination(int page, int pageSize);
    int getTotalCount();
    int deleteStudent(String id);
    int updataStudent(Student student);
    List<Student> findByName(String studentID,String stuName,int page, int pageSize);
    int getTotalCountByName(String stuName);

}
