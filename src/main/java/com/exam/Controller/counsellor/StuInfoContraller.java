package com.exam.Controller.counsellor;

import com.exam.Service.counsellor.StuInfoService;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stu")
public class StuInfoContraller {
    @Autowired
    private StuInfoService stuInfoService;

//显示所有信息
    @GetMapping("/show")
    public List showStudent(){
        List<Student> stus = stuInfoService.showStudent();
        return stus;
    }

//    CM07-01
//    功能名称： 添加学生信息模块
    @PostMapping("/addStudent")
    public String addDepinfo(@RequestBody Student student){
        int i = stuInfoService.addStudentinfo(student);

        return "添加成功";
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id){
        int i = stuInfoService.deleteStudent(id);
        return "成功";
    }


    //    CM07-03
//    功能名称： 修改学生信息模块
    @PostMapping("/updataStudent")
    public String updataStudent(Student student){
        int i = stuInfoService.updataStudent(student);
        if (i!=0){
            return "成功";
        }else {
            return "失败";
        }
    }


    //    CM07-04
//    功能名称： 查询学生信息模块
    @GetMapping("/likename/{name}")
    public List showStudent(@PathVariable String name){
        List<Student> byName = stuInfoService.findByName(name);
        return byName;
    }
}
