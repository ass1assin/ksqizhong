package com.exam.Controller.manager;


import com.exam.Service.manager.UserService;
import com.exam.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


//    ------CM01:-------
    @PostMapping("/login")
    public String login(@RequestBody String username,@RequestBody String password){

        User user1 = userService.findInfo(username, password);
        System.out.println(user1);
        if (user1 == null){
//         用户名或密码错误返回登录界面
            return null;
    }
//         登录成功
            return "成功";
}


}

