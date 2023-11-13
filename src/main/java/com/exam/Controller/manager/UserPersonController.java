package com.exam.Controller.manager;


import com.exam.Service.manager.UserService;
import com.exam.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/person")
public class UserPersonController {
    @Autowired
    private UserService userService;


//    -------CM02: 个人设置-显示信息------
    @GetMapping("/showInfo")
    public ModelAndView adminInfo(@RequestParam String fullname, @RequestParam String password){
        ModelAndView modelAndView =new ModelAndView();


        User user1 = userService.findInfo(fullname, password);

        modelAndView.addObject("user",user1);
        modelAndView.setViewName("admin/personinfomation");
        return modelAndView;
    }


//   ------- CM02: 个人设置-修改信息-------
    @PostMapping("/updataInfo")
    public ModelAndView updataInfo(@ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView("admin/personinfomation");
        int updataUser = userService.updataUser(user);
        String message = null;
        if (updataUser !=0){
            message="更新成功";
        }else {
            message="更新失败";
        }
        modelAndView.addObject("message",message);
        return modelAndView;
    }


}

