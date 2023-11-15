package com.exam.Service.manager.Impl;



import com.exam.Service.manager.UserService;
import com.exam.dao.manager.UserDao;
import com.exam.entity.Inst;
import com.exam.entity.Student;
import com.exam.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User findInfo(String fullname, String password) {
        if (StringUtils.isEmpty(fullname) || StringUtils.isEmpty(password)){
            return null;
        }else {
            User user = userDao.findUser(fullname, password);
            return user;
        }
    }
    @Override
    public Student findStudent(String stuName, String stuID) {
        if (StringUtils.isEmpty(stuName) || StringUtils.isEmpty(stuID)){
            return null;
        }else {
            Student student = userDao.findStudent(stuName, stuID);
            return student;
        }
    }

    @Override
    public Inst findInst(String instName, String instID) {
        if (StringUtils.isEmpty(instName) || StringUtils.isEmpty(instID)){
            return null;
        }else {
            Inst inst = userDao.findInst(instName,instID);
            return inst;
        }
    }

    @Override
    public int updataUser(User user) {
        int  updata = userDao.updataUser(user);

        return updata;
    }



}
