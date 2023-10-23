package com.exam.Controller;


import com.exam.Service.UserService;
import com.exam.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


//    ------CM01:-------
    @PostMapping("/login")
    public String login(@RequestBody User user){
        String fullname = user.getFullname();
        String password = user.getPassword();

        User user1 = userService.findInfo(fullname, password);
        System.out.println(user1);
        if (user1 == null){
//         用户名或密码错误返回登录界面
            return "login";
    }
//         登录成功
            return "index";
}


}

