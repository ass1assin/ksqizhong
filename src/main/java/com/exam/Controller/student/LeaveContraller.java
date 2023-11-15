package com.exam.Controller.student;


import com.exam.Service.student.LeaveService;

import com.exam.entity.Leave;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
        ModelAndView modelAndView = new ModelAndView("counsellor/leavemanage");

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
    public ModelAndView audit(@ModelAttribute Leave leave) {
        int audit = leaveService.audit(leave);
        ModelAndView modelAndView = new ModelAndView("redirect:/leave/showLeave");
        return modelAndView;
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        // 获取请假数据
        List<Leave> leaveList = leaveService.showLeave();

//        System.out.println("sssssssssssssssssss" + leaveList);
        // 创建工作簿
        try (Workbook workbook = new XSSFWorkbook()) {
            // 创建工作表
            Sheet sheet = workbook.createSheet("Leave Data");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("请假单号");
            headerRow.createCell(1).setCellValue("课程ID");
            headerRow.createCell(2).setCellValue("请假单原因");
            headerRow.createCell(3).setCellValue("请假天数");
            headerRow.createCell(4).setCellValue("请假人学号");
            headerRow.createCell(5).setCellValue("申请时间");
            headerRow.createCell(6).setCellValue("请假单状态");
            headerRow.createCell(7).setCellValue("审核时间");
            headerRow.createCell(8).setCellValue("导员意见");

            // 添加数据
            int rowNum = 1;
            for (Leave leave : leaveList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(leave.getLeaveID());
                row.createCell(1).setCellValue(leave.getCourseID());
                row.createCell(2).setCellValue(leave.getReason());
                row.createCell(3).setCellValue(leave.getDaynum());
                row.createCell(4).setCellValue(leave.getStuNo());
                row.createCell(5).setCellValue(leave.getApplytime().toString());
                // 根据请假单状态设置相应的字符串
                String statusLabel = "";
                switch (leave.getStatus()) {
                    case "0":
                        statusLabel = "待审核";
                        break;
                    case "1":
                        statusLabel = "审核通过";
                        break;
                    case "2":
                        statusLabel = "审核不通过";
                        break;
                }
                row.createCell(6).setCellValue(statusLabel);
                row.createCell(7).setCellValue(leave.getAudittime() != null ? leave.getAudittime().toString() : ""); // 根据实际情况格式化日期
                row.createCell(8).setCellValue(leave.getOpinion());
            }

            // 设置响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=leave_data.xlsx");
            response.setCharacterEncoding("UTF-8");

            // 将工作簿写入响应流
            try {
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}