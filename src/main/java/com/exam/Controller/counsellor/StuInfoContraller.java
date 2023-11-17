package com.exam.Controller.counsellor;

import com.exam.Service.counsellor.StuInfoService;
import com.exam.entity.Classes;
import com.exam.entity.Department;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StuInfoContraller {
    @Autowired
    private StuInfoService stuInfoService;


    @GetMapping("/showStudent")
    public ModelAndView showDep(@RequestParam(name = "page",defaultValue = "1") int page,
                                @RequestParam(name = "pageSize",defaultValue = "10") int pageSize) {
        ModelAndView modelAndView = new ModelAndView("counsellor/student_manage");

        List<Classes> classes = stuInfoService.showClasses();
        modelAndView.addObject("classes", classes);

        List<Student> stuInfo = stuInfoService.getDepsWithPagination(page, pageSize);
        int totalPages = stuInfoService.getTotalPages(pageSize);

        modelAndView.addObject("stuInfo", stuInfo);

        modelAndView.addObject("totalPages", totalPages);

        return modelAndView;
    }

//    CM07-01
//    功能名称： 添加学生信息模块
    @PostMapping("/addStudent")
    public ModelAndView addDepinfo(@ModelAttribute Student student, RedirectAttributes redirectAttributes){
        int i = stuInfoService.addStudentinfo(student);
        ModelAndView modelAndView = new ModelAndView("redirect:/student/showStudent");
        redirectAttributes.addFlashAttribute("successMessage", "操作成功");
        return modelAndView;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @GetMapping("/delete")
    public ModelAndView deleteStudent( String deleteStudentID, RedirectAttributes redirectAttributes){
        int i = stuInfoService.deleteStudent(deleteStudentID);
        ModelAndView modelAndView = new ModelAndView("redirect:/student/showStudent");
        redirectAttributes.addFlashAttribute("successMessage", "操作成功");
        return modelAndView;
    }


    //    CM07-03
//    功能名称： 修改学生信息模块
//    @PostMapping("/updataStudent")
//    public String updataStudent(Student student){
//        int i = stuInfoService.updataStudent(student);
//        if (i!=0){
//            return "成功";
//        }else {
//            return "失败";
//        }
//    }
    @PostMapping("/updataStudent")
    public ModelAndView updataStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes){
        int i = stuInfoService.updataStudent(student);
        ModelAndView modelAndView = new ModelAndView("redirect:/student/showStudent");
        redirectAttributes.addFlashAttribute("successMessage", "操作成功");
        String message = null;

        return modelAndView;
    }


    //    CM07-04
//    功能名称： 查询学生信息模块
//    @GetMapping("/likename/{name}")
//    public List showStudent(@PathVariable String name){
//        List<Student> byName = stuInfoService.findByName(name);
//        return byName;
//    }
    @GetMapping("/likename")
    public ModelAndView showStudent(String stuName,String stuID,
                                @RequestParam(name = "page",defaultValue = "1") int page,
                                @RequestParam(name = "pageSize",defaultValue = "10") int pageSize){
        ModelAndView modelAndView = new ModelAndView("coursemanager/student_manage");

        List<Classes> classes = stuInfoService.showClasses();
        modelAndView.addObject("classes", classes);


        List<Student> students = stuInfoService.findByName(stuID,stuName,page,pageSize);
        int totalPages = stuInfoService.getTotalPagesByName(pageSize,stuName);

        Boolean pageIf =true;
//              根据条件查询到的数据
        modelAndView.addObject("students", students);


//              前端根据likename显示文本
        modelAndView.addObject("likeName",stuName);
        modelAndView.addObject("likeID",stuID);

        modelAndView.addObject("totalPages", totalPages);
        return modelAndView;
    }
}
