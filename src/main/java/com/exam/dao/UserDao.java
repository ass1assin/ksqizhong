package com.exam.dao;


import com.exam.entity.User;

public interface UserDao {
    User findUser(String fullname,String password);
    int updataUser(User user);
}
