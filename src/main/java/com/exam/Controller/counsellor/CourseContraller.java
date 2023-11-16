package com.exam.Controller.counsellor;

import com.exam.Service.counsellor.ClassService;
import com.exam.Service.counsellor.CourseService;
import com.exam.entity.Classes;
import com.exam.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseContraller {
    @Autowired
    private CourseService courseService;

//显示所有信息
//    @GetMapping("/showCourse")
//    public List showCourse(){
//        List<Course> CourseList = courseService.showCourse();
//        return CourseList;
//    }
    @GetMapping("/showCourse")
    public ModelAndView showCourse(@RequestParam(name = "page",defaultValue = "1") int page,
                                @RequestParam(name = "pageSize",defaultValue = "10") int pageSize) {
    ModelAndView modelAndView = new ModelAndView("counsellor/course_manage");

    List<Course> courses = courseService.getDepsWithPagination(page, pageSize);
    int totalPages = courseService.getTotalPages(pageSize);

    modelAndView.addObject("courses", courses);

    modelAndView.addObject("totalPages", totalPages);

    return modelAndView;
    }

//    CM07-01
//    功能名称： 添加学生信息模块
    @PostMapping("/addCourse")
    public ModelAndView addCourse(@ModelAttribute Course course){
        int i =courseService.addCourse(course);
        ModelAndView modelAndView = new ModelAndView("redirect:/course/showCourse");
        return modelAndView;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @GetMapping("/delete")
    public ModelAndView deleteCourse(String deleteCourseID){
        int i =courseService.deleteCourse(deleteCourseID);
        ModelAndView modelAndView = new ModelAndView("redirect:/course/showCourse");
        return modelAndView;
    }


    //    CM07-03
//    功能名称： 修改学生信息模块
    @PostMapping("/updataCourse")
    public ModelAndView updataCourse(@ModelAttribute Course course){
        int i = courseService.updataCourse(course);
        ModelAndView modelAndView=new ModelAndView("redirect:/course/showCourse");

        return modelAndView;
    }


    //    CM07-04
//    功能名称： 查询学生信息模块
//    @GetMapping("/likename/{name}")
//    public List showCourse(@PathVariable String name){
//        List<Course> byName = courseService.findByName(name);
//        return byName;
//    }
    @GetMapping("/likename")
    public ModelAndView showCourse(String courseName,
                                @RequestParam(name = "page",defaultValue = "1") int page,
                                @RequestParam(name = "pageSize",defaultValue = "10") int pageSize){
        ModelAndView modelAndView = new ModelAndView("counsellor/course_manage");

        List<Course> courses = courseService.findByName(courseName,page,pageSize);

        int totalPages = courseService.getTotalPagesByName(pageSize,courseName);

        Boolean pageIf =true;
//              根据条件查询到的数据
        modelAndView.addObject("courses", courses);
//              前端根据likename显示文本
        modelAndView.addObject("likeName",courseName);

        modelAndView.addObject("totalPages", totalPages);
        return modelAndView;
    }
}
