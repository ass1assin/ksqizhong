package com.exam.Service.Impl;



import com.exam.Service.UserService;
import com.exam.dao.UserDao;
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
    public int updataUser(User user) {
        int  updata = userDao.updataUser(user);

        return updata;
    }


}
