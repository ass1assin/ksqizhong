package com.exam.Controller.counsellor;

import com.exam.Service.counsellor.ClassService;
import com.exam.Service.counsellor.StuInfoService;
import com.exam.entity.Classes;
import com.exam.entity.Inst;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassContraller {
    @Autowired
    private ClassService classService;

//显示所有信息
    @GetMapping("/showClass")

    public ModelAndView showInst(@RequestParam(name = "page",defaultValue = "1") int page,
                                 @RequestParam(name = "pageSize",defaultValue = "10") int pageSize) {
        ModelAndView modelAndView = new ModelAndView("counsellor/classes_manage");

        List<Classes> classes = classService.getClassesWithPagination(page, pageSize);
        int totalPages = classService.getTotalPages(pageSize);

        modelAndView.addObject("classes",classes);

        modelAndView.addObject("totalPages", totalPages);

        return modelAndView;
    }
//    CM07-01
//    功能名称： 添加学生信息模块
    @PostMapping("/addClass")
    public ModelAndView addInst(@ModelAttribute Classes classes, RedirectAttributes redirectAttributes){
        int i = classService.addClasses(classes);
        ModelAndView modelAndView = new ModelAndView("redirect:/class/showClass");
        redirectAttributes.addFlashAttribute("successMessage", "操作成功");
        return modelAndView;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @GetMapping("/delete")
    public ModelAndView deleteDep(String deleteClassID,RedirectAttributes redirectAttributes){
        int i = classService.deleteClasses(deleteClassID);
        ModelAndView modelAndView = new ModelAndView("redirect:/class/showClass");
        redirectAttributes.addFlashAttribute("successMessage", "操作成功");
        return modelAndView;
    }

    //    CM07-03
//    功能名称： 修改学生信息模块
    @PostMapping("/updataClass")
    public ModelAndView updataDep(@ModelAttribute Classes classes,RedirectAttributes redirectAttributes){
        int i = classService.updataClasses(classes);
        ModelAndView modelAndView = new ModelAndView("redirect:/class/showClass");
        redirectAttributes.addFlashAttribute("successMessage", "操作成功");
        return modelAndView;
    }



    //    CM07-04
//    功能名称： 查询学生信息模块
    @GetMapping("/likename")
    public ModelAndView showDep(String className,
                                @RequestParam(name = "page",defaultValue = "1") int page,
                                @RequestParam(name = "pageSize",defaultValue = "10") int pageSize){
        ModelAndView modelAndView = new ModelAndView("counsellor/classes_manage");

        List<Classes> classes = classService.findByName(className,page,pageSize);

        int totalPages = classService.getTotalPagesByName(pageSize,className);

//              根据条件查询到的数据
        modelAndView.addObject("classes",classes);
//              前端根据likename显示文本
        modelAndView.addObject("likeName",className);

        modelAndView.addObject("totalPages", totalPages);
        return modelAndView;
    }
}
