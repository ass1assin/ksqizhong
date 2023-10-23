package com.exam.Controller;


import com.exam.Service.UserService;
import com.exam.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class UserPersonController {
    @Autowired
    private UserService userService;


//    -------CM02: 个人设置-显示信息------
    @PostMapping("/showInfo")
    public User adminInfo(@RequestBody User user){
        String fullname = user.getFullname();
        String password = user.getPassword();
        User user1 = userService.findInfo(fullname, password);
        return user1;
    }

//   ------- CM02: 个人设置-修改信息-------
    @PostMapping("/updataInfo")
    public String updataInfo(@RequestBody User user){

        int updataUser = userService.updataUser(user);

        if (updataUser !=0){

            return "成功";
        }else {
            return "失败";
        }
    }


}

