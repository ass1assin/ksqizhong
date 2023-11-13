package com.exam.Controller.counsellor;

import com.exam.Service.counsellor.ClassService;
import com.exam.Service.counsellor.StuInfoService;
import com.exam.entity.Classes;
import com.exam.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassContraller {
    @Autowired
    private ClassService classService;

//显示所有信息
    @GetMapping("/showClass")
    public List showClass(){
        List<Classes> classesList = classService.showClass();
        return classesList;
    }

//    CM07-01
//    功能名称： 添加学生信息模块
    @PostMapping("/addClass")
    public String addClass(@RequestBody Classes classes){
        int i = classService.addClass(classes);

        return "添加成功";
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @DeleteMapping("/delete/{id}")
    public String deleteClass(@PathVariable List<Integer> ids){
        int i = classService.deleteClass(ids);
        return "成功";
    }


    //    CM07-03
//    功能名称： 修改学生信息模块
    @PostMapping("/updataClass")
    public String updataStudent(Classes classes){
        int i = classService.updataClass(classes);
        if (i!=0){
            return "成功";
        }else {
            return "失败";
        }
    }


    //    CM07-04
//    功能名称： 查询学生信息模块
    @GetMapping("/likename/{name}")
    public List showClass(@PathVariable String name){
        List<Classes> byName = classService.findByName(name);
        return byName;
    }
}
