package com.exam.Service;



import com.exam.entity.Inst;
import com.exam.entity.Student;
import com.exam.entity.User;

public interface UserService {
     User findInfo(String fullname,String password);
     int updataUser(User user);
     Student findStudent(String stuName, String stuID);
     Inst findInst(String instName, String instID);
}