package com.exam.Controller.manager;

import com.exam.Service.manager.InstInfoService;
import com.exam.entity.Department;
import com.exam.entity.Inst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/inst")
public class InstInfoContraller {
    @Autowired
    private InstInfoService instInfoService;

//显示所有信息
    @GetMapping("/showInst")

    public ModelAndView showInst(@RequestParam(name = "page",defaultValue = "1") int page,
                                @RequestParam(name = "pageSize",defaultValue = "10") int pageSize) {
        ModelAndView modelAndView = new ModelAndView("admin/instructor_manage");

        List<Inst> Insts = instInfoService.getDepsWithPagination(page, pageSize);
        int totalPages = instInfoService.getTotalPages(pageSize);

        modelAndView.addObject("Insts", Insts);

        modelAndView.addObject("totalPages", totalPages);

        return modelAndView;
    }

//    CM03-01
//    功能名称： 添加辅导员信息模块
    @PostMapping("/addInst")
    public ModelAndView addInst(@ModelAttribute Inst inst){
        int i = instInfoService.addInst(inst);
        ModelAndView modelAndView = new ModelAndView("redirect:/inst/showInst");
        return modelAndView;
    }


    //    CM03-02
//    功能名称： 删除辅导员信息模块
    @GetMapping("/delete")
    public ModelAndView deleteDep(String deleteInstID){
        int i = instInfoService.deleteInst(deleteInstID);
        ModelAndView modelAndView = new ModelAndView("redirect:/inst/showInst");
        return modelAndView;
    }


    //    CM03-03
//    功能名称： 修改辅导员信息模块
    @PostMapping("/updataInst")
    public ModelAndView updataDep(@ModelAttribute Inst inst){
        int i = instInfoService.updataInst(inst);
        ModelAndView modelAndView = new ModelAndView("redirect:/inst/showInst");
        String message = null;
        if (i!=0){
            message="更新成功";
        }else {
            message="更新失败";
        }
        modelAndView.addObject("message",message);
        return modelAndView;
    }



    //    CM03-04
//    功能名称： 查询辅导员信息模块
    @GetMapping("/likename")
    public ModelAndView showDep(String instName,
                                @RequestParam(name = "page",defaultValue = "1") int page,
                                @RequestParam(name = "pageSize",defaultValue = "10") int pageSize){
        ModelAndView modelAndView = new ModelAndView("admin/instructor_manage");

        List<Inst> Insts = instInfoService.findByName(instName,page,pageSize);

        int totalPages = instInfoService.getTotalPagesByName(pageSize,instName);

//              根据条件查询到的数据
        modelAndView.addObject("Insts",Insts);
//              前端根据likename显示文本
        modelAndView.addObject("likeName",instName);

        modelAndView.addObject("totalPages", totalPages);
        return modelAndView;
    }
}
