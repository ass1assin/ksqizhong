package com.exam.Controller.manager;


import com.exam.Service.manager.UserService;
import com.exam.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLoginController {
    @Autowired
    private UserService userService;


//    ------CM01:-------
    @PostMapping("/login")
    public ModelAndView login(@RequestBody String username, @RequestBody String password){
        ModelAndView modelAndView =new ModelAndView();
        User user1 = userService.findInfo(username, password);
        System.out.println("ssssssssssssssssssssssssssssssssssssss"+user1);
        if (user1 == null){
//         用户名或密码错误返回登录界面
            modelAndView.setViewName("login");
    }
        else {
            //         登录成功
            modelAndView.setViewName("main");
        }
        return modelAndView;

}


}

