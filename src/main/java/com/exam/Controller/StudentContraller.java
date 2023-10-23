package com.exam.Controller;

import com.exam.Service.StudentService;
import com.exam.entity.Dep;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentContraller {
    @Autowired
    private StudentService studentService;

//显示所有信息
    @GetMapping
    public List showStudent(){
        List<Student> stus = studentService.showStudent();
        return stus;
    }

//    CM07-01
//    功能名称： 添加学生信息模块
    @PostMapping("/addStudent")
    public String addDepinfo(@RequestBody Student student){
        int i = studentService.addStudentinfo(student);

        return "添加成功";
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id){
        int i = studentService.deleteStudent(id);
        return "成功";
    }


    //    CM07-03
//    功能名称： 修改学生信息模块
    @PostMapping("updataStudent")
    public String updataStudent(Student student){
        int i = studentService.updataStudent(student);
        if (i!=0){
            return "成功";
        }else {
            return "失败";
        }
    }


    //    CM07-04
//    功能名称： 查询学生信息模块
    @GetMapping("/likename")
    public List showStudent(@PathVariable String name){
        List<Student> byName = studentService.findByName(name);
        return byName;
    }
}
