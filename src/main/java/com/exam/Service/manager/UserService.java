package com.exam.Service.manager;



import com.exam.entity.User;

public interface UserService {
     User findInfo(String fullname,String password);
     int updataUser(User user);
}