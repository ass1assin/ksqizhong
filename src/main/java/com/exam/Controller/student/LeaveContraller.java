package com.exam.Controller.student;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.exam.Service.counsellor.CourseService;
import com.exam.Service.student.LeaveService;
import com.exam.entity.Course;
import com.exam.entity.Department;
import com.exam.entity.Leave;
import com.exam.entity.LeaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveContraller {
    @Autowired
    private LeaveService leaveService;

    //显示所有信息
    @GetMapping("/showLeave")
    public ModelAndView showDep(@RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        ModelAndView modelAndView = new ModelAndView("admin/leavemanage");

        List<Leave> leaves = leaveService.getLeavesWithPagination(page, pageSize);
        int totalPages = leaveService.getTotalPages(pageSize);
        System.out.println(leaves);
        modelAndView.addObject("leaves", leaves);

        modelAndView.addObject("totalPages", totalPages);

        return modelAndView;
    }


    //    CM07-01
//    功能名称： 添加学生信息模块
    @PostMapping("/addLeave")
    public ModelAndView addDepinfo(@ModelAttribute Leave leave) {
        int i = leaveService.addLeave(leave);
        System.out.println("ssssssssssss创建前"+leave);
        ModelAndView modelAndView = new ModelAndView("redirect:/leave/showLeave");
        return modelAndView;
    }


    //    CM07-02
//    功能名称： 删除学生信息模块
    @GetMapping("/delete")
    public ModelAndView deleteDep(String deleteLeaveID) {
        int i = leaveService.deleteLeave(deleteLeaveID);
        ModelAndView modelAndView = new ModelAndView("redirect:/leave/showLeave");
        return modelAndView;
    }


    @PostMapping("/auditLeave")
    public int audit(@PathVariable Leave leave) {
        int audit = leaveService.audit(leave);
        return audit;
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Leave> leaveList = leaveService.showLeave();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setCharacterEncoding("utf-8");
//        String fileName = URLEncoder.encode("测试数据", "UTF-8").replaceAll("\\+", "%20");
        String fileName = "测试数据";
        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xlsx");
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        try (ServletOutputStream out = response.getOutputStream()) {
            // 导出Excel
            EasyExcel.write(out, Leave.class)
                    .head(Leave.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("请假单表")
                    .doWrite(leaveList);
        } catch (Exception e) {
            e.printStackTrace(); // 可以使用更具体的日志记录方法
            throw new RuntimeException("Error exporting data", e);
        }
    }
}