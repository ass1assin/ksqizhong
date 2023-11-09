package com.exam.Controller.manager;

import com.exam.Service.manager.DepService;
import com.exam.entity.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/dep")
public class DepContraller {
    @Autowired
    private DepService depService;

//显示所有信息
    @GetMapping("/showDep")
    public ModelAndView showDep(){
        ModelAndView modelAndView = new ModelAndView();
        List<Dep> deps = depService.showDep();
        System.out.println(deps);
        modelAndView.addObject("deps",deps);
        modelAndView.setViewName("index_v1");
        return modelAndView;
    }

//    CM03-01
//    功能名称： 添加二级学院信息模块
//    @GetMapping("/addPage")
//    public ModelAndView addPage(Model model){
//        model.addAttribute("dep",new Dep());
//        return new ModelAndView("add","depmodel",model)
//    }

    @PostMapping("/addDep")
    public ModelAndView addDepinfo(@ModelAttribute Dep dep){
        int i = depService.addDepinfo(dep);
        ModelAndView modelAndView = new ModelAndView("redirect:/dep/showDep");
        return modelAndView;
    }


    //    CM03-02
//    功能名称： 删除二级学院信息模块
    @GetMapping("/{id}")
    public ModelAndView deleteDep(@PathVariable List<String> ids){
        int i = depService.deleteDep(ids);
        ModelAndView modelAndView = new ModelAndView("redirect:/dep/showDep");
        return modelAndView;
    }


    //    CM03-03
//    功能名称： 修改二级学院信息模块
    @PostMapping("/updataDep")
    public String updataDep(Dep dep){
        int i = depService.updataDep(dep);
        if (i!=0){
            return "成功";
        }else {
            return "失败";
        }
    }


    //    CM03-04
//    功能名称： 查询二级学院信息模块
    @GetMapping("/likename")
    public List showDep(@PathVariable String name){
        List<Dep> byName = depService.findByName(name);
        return byName;
    }
}
