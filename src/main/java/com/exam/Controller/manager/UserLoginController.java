package com.exam.Controller.manager;


import com.exam.Service.manager.DepService;
import com.exam.Service.manager.UserService;
import com.exam.entity.Dep;
import com.exam.entity.User;
//import com.exam.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepService depService;

//    ------CM01:-------
    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password, HttpSession session){
        ModelAndView modelAndView =new ModelAndView();
        User user1 = userService.findInfo(username, password);

        if (user1 == null){
//         用户名或密码错误返回登录界面
            modelAndView.setViewName("login");
    }
        else {

            List<Dep> deps = depService.showDep();
            session.setAttribute("userLoggedIn", true);
            //         登录成功
            modelAndView.addObject("deps", deps);
            modelAndView.addObject("user",user1);
            modelAndView.setViewName("/common/index");
        }
        return modelAndView;
    }

}

