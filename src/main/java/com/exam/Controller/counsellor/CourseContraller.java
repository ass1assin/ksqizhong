package com.exam.Controller.counsellor;

import com.exam.Service.counsellor.ClassService;
import com.exam.Service.counsellor.CourseService;
import com.exam.entity.Classes;
import com.exam.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseContraller {
    @Autowired
    private CourseService courseService;

//显示所有信息
    @GetMapping("/showCourse")
    public List showCourse(){
        List<Course> CourseList = courseService.showCourse();
        return CourseList;
    }

//    CM07-01
//    功能名称： 添加学生信息模块
    @PostMapping("/addCourse")
    public String addCourse(@RequestBody Course course){
        int i =courseService.addCourse(course);

        return "添加成功";
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable List<Integer> ids){
        int i =courseService.deleteCourse(ids);
        return "成功";
    }


    //    CM07-03
//    功能名称： 修改学生信息模块
    @PostMapping("/updataCourse")
    public String updataCourse(Course course){
        int i = courseService.updataCourse(course);
        if (i!=0){
            return "成功";
        }else {
            return "失败";
        }
    }


    //    CM07-04
//    功能名称： 查询学生信息模块
    @GetMapping("/likename/{name}")
    public List showCourse(@PathVariable String name){
        List<Course> byName = courseService.findByName(name);
        return byName;
    }
}
