package com.exam.Controller.manager;


import com.exam.Service.manager.DepartmentService;
import com.exam.Service.manager.UserService;

import com.exam.entity.Department;
import com.exam.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.Enumeration;
import java.util.List;

@Controller
public class UserLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;

//    ------CM01:-------
    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password,@RequestParam String type, HttpSession session){
        ModelAndView modelAndView =new ModelAndView();
        User user1 = userService.findInfo(username, password);
        user1.setType(type);

        if (user1 == null){
//         用户名或密码错误返回登录界面
            modelAndView.setViewName("login");
    }
        else {

            List<Department> deps = departmentService.showDep();
            session.setAttribute("userLoggedIn", true);
            //         登录成功
            modelAndView.addObject("deps", deps);
            modelAndView.addObject("user",user1);
            modelAndView.setViewName("/common/index");

        }
        return modelAndView;
    }

}

