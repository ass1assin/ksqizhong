package com.exam.Controller.manager;

import com.exam.entity.Department;
import com.exam.Service.manager.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/department")
  public class DepartmentContraller {
      @Autowired
      private DepartmentService departmentService;

      //显示所有信息
      @GetMapping("/showDep")

      public ModelAndView showDep(@RequestParam(name = "page",defaultValue = "1") int page,
                                  @RequestParam(name = "pageSize",defaultValue = "10") int pageSize
                                  ) {
          ModelAndView modelAndView = new ModelAndView("admin/department_manage");

          List<Department> departments = departmentService.getDepsWithPagination(page, pageSize);
          int totalPages = departmentService.getTotalPages(pageSize);

          modelAndView.addObject("departments", departments);
          modelAndView.addObject("totalPages", totalPages);



          return modelAndView;
      }


//  CM03-01
//  功能名称： 添加二级学院信息模块


      @PostMapping("/addDep")

          public ModelAndView addDepinfo(@ModelAttribute Department department, RedirectAttributes redirectAttributes){
              int i = departmentService.addDepinfo(department);
              ModelAndView modelAndView = new ModelAndView("redirect:/department/showDep");
              redirectAttributes.addFlashAttribute("successMessage", "操作成功");
              return modelAndView;
          }


          //    CM03-02
//  功能名称： 删除二级学院信息模块

          @GetMapping("/delete")
          public ModelAndView deleteDep( String deleteDepID,RedirectAttributes redirectAttributes){
              int i = departmentService.deleteDep(deleteDepID);
              ModelAndView modelAndView = new ModelAndView("redirect:/department/showDep");
              redirectAttributes.addFlashAttribute("successMessage", "操作成功");
              return modelAndView;
          }



          //    CM03-03
//  功能名称： 修改二级学院信息模块
          @PostMapping("/updataDep")
          public ModelAndView updataDep(@ModelAttribute Department department,RedirectAttributes redirectAttributes){
              int i = departmentService.updataDep(department);
              ModelAndView modelAndView = new ModelAndView("redirect:/department/showDep");
             redirectAttributes.addFlashAttribute("successMessage", "操作成功");
              return modelAndView;
          }


          //    CM03-04
//  功能名称： 查询二级学院信息模块
          @GetMapping("/likename")
          public ModelAndView showDep(String depName,String depID,
                                      @RequestParam(name = "page",defaultValue = "1") int page,
                                      @RequestParam(name = "pageSize",defaultValue = "10") int pageSize){
              ModelAndView modelAndView = new ModelAndView("admin/department_manage");

              List<Department> departments = departmentService.findByName(depID,depName,page,pageSize);

              int totalPages = departmentService.getTotalPagesByName(pageSize,depName);

              Boolean pageIf =true;
//              根据条件查询到的数据
              modelAndView.addObject("departments", departments);
//              前端根据likename显示文本
              modelAndView.addObject("likeName",depName);
              modelAndView.addObject("likeID",depID);


                modelAndView.addObject("totalPages", totalPages);
                return modelAndView;
            }
        }
