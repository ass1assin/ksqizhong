package com.exam.Controller.manager;


import com.exam.Service.manager.DepartmentService;
import com.exam.Service.manager.UserService;

import com.exam.entity.Department;
import com.exam.entity.User;
//import com.exam.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
//怎么使用呢
//            Map<String,Object> claims=new HashMap<>();
//            claims.put("username",user1.getFullname());
//            String jwtToken= JwtUtils.generateJWT(claims);
//
//            Cookie cookie = new Cookie("jwtToken", jwtToken);
//            cookie.setPath("/"); // 设置 Cookie 的路径，确保在整个应用程序中都可以访问
////            cookie.setHttpOnly(true); // 设置 HttpOnly 属性，提高安全性
//            response.addCookie(cookie);

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

