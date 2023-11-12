package com.exam.Controller.manager;

import com.exam.entity.Department;
import com.exam.Service.manager.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
    @RequestMapping("/department")
    public class DepartmentContraller {
        @Autowired

        private DepartmentService departmentService;

        //显示所有信息
        @GetMapping("/showDep")

        public ModelAndView showDep(@RequestParam(name = "page",defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize",defaultValue = "10") int pageSize) {
            ModelAndView modelAndView = new ModelAndView("admin/collegemanage");

            List<Department> departments = departmentService.getDepsWithPagination(page, pageSize);
            int totalPages = departmentService.getTotalPages(pageSize);

            modelAndView.addObject("departments", departments);

            modelAndView.addObject("totalPages", totalPages);

            return modelAndView;
        }


//    CM03-01
//    功能名称： 添加二级学院信息模块


        @PostMapping("/addDep")

            public ModelAndView addDepinfo(@ModelAttribute Department department){
                int i = departmentService.addDepinfo(department);
                ModelAndView modelAndView = new ModelAndView("redirect:/department/showDep");
                return modelAndView;
            }


            //    CM03-02
//    功能名称： 删除二级学院信息模块

            @GetMapping("/delete")
            public ModelAndView deleteDep( String deleteDepID){
                int i = departmentService.deleteDep(deleteDepID);
                ModelAndView modelAndView = new ModelAndView("redirect:/department/showDep");
                return modelAndView;
            }



            //    CM03-03
//    功能名称： 修改二级学院信息模块
            @PostMapping("/updataDep")
            public ModelAndView updataDep(Department department){
                int i = departmentService.updataDep(department);
                ModelAndView modelAndView = new ModelAndView("redirect:/department/showDep");
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
//    功能名称： 查询二级学院信息模块
            @GetMapping("/likename")
            public ModelAndView showDep(@RequestParam(name = "depName")String depName){
                ModelAndView modelAndView = new ModelAndView();
                List<Department> departments = departmentService.findByName(depName);
                modelAndView.addObject("deps", departments);
                modelAndView.setViewName("collegemanage");
                return modelAndView;
            }
        }
