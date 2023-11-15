package com.exam.dao.manager.Impl;


import com.exam.dao.manager.UserDao;
import com.exam.entity.Inst;
import com.exam.entity.Student;
import com.exam.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public User findUser(String fullname, String password) {
        try {
            String selectsql = "select * from sys_user where fullname=? and password=?";
            User user = jdbcTemplate.queryForObject(selectsql, new BeanPropertyRowMapper<>(User.class), fullname, password);
            return user;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Student findStudent(String stuName, String stuID) {
        try {
            String selectsql = "select * from sys_student where stuName=? and stuID=?";
            Student student = jdbcTemplate.queryForObject(selectsql, new BeanPropertyRowMapper<>(Student.class), stuName, stuID);
            return student;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Inst findInst(String instName, String instID) {
        try {
            String selectsql = "select * from sys_instructor where instName=? and instID=?";
            Inst inst = jdbcTemplate.queryForObject(selectsql, new BeanPropertyRowMapper<>(Inst.class), instName, instID);
            return inst;
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public int updataUser(User user) {
        String sql = "update sys_user set fullname=? ,password=? ,telephone=? where userid=?";

        Object[] user1 = {user.getFullname(), user.getPassword(), user.getTelephone(), user.getUserid()};

        //调用jdbcTemplate.update(实现添加 删除 修改等)
        int update = jdbcTemplate.update(sql, user1);

        return update;
    }

}