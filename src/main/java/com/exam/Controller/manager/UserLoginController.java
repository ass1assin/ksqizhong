package com.exam.Controller.manager;


import com.exam.Service.manager.DepartmentService;
import com.exam.Service.manager.UserService;

import com.exam.entity.Department;
import com.exam.entity.Inst;
import com.exam.entity.Student;
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


//    ------CM01:-------
    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password,@RequestParam String type, HttpSession session){
        ModelAndView modelAndView =new ModelAndView();
        User user1 = new User();
        Inst inst = new Inst();
        Student student = new Student();
        String loginName = null;
        switch (type) {
            case "admin":
                user1 = userService.findInfo(username, password);

                if (user1 == null){
                //         用户名或密码错误返回登录界面
                    modelAndView.setViewName("/common/login");
                }
                else {
                    loginName=user1.getFullname();
                    user1.setType(type);
                    modelAndView.setViewName("/common/index");
                    //         登录成功
                }
                break;
            case "teacher":
                 inst = userService.findInst(username, password);

                if (inst == null){
                    //         用户名或密码错误返回登录界面
                    modelAndView.setViewName("/common/login");
                }
                else {
                    loginName=inst.getInstName();
                    inst.setType(type);
                    modelAndView.setViewName("/common/index");
                    //         登录成功
                }
                break;
            case "student":
                 student = userService.findStudent(username, password);

                if (student == null){
                    //         用户名或密码错误返回登录界面
                    modelAndView.setViewName("/common/login");
                }
                else {
                    loginName=student.getStuName();

                    student.setType(type);
                    modelAndView.setViewName("/common/index");
                    //         登录成功
                }
                break;
        }
        modelAndView.addObject("user",user1);
        modelAndView.addObject("inst",inst);
        modelAndView.addObject("student",student);
        modelAndView.addObject("stuID",student.getStuID());
        modelAndView.addObject("loginName",loginName);

        modelAndView.addObject("loginSuccess", true);

        session.setAttribute("userLoggedIn", true);

        return modelAndView;
    }

    @GetMapping("/hellow")
    public ModelAndView hellow(){
        ModelAndView modelAndView =new ModelAndView("/common/Hellow");
        return modelAndView;
    }
}

