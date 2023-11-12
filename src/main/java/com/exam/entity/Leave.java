package com.exam.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ExcelIgnoreUnannotated
public class Leave {
    @ExcelProperty("请假单号")
    private String leaveID;
    @ExcelProperty("课程ID")
    private String courseID;
    @ExcelProperty("请假单原因")
    private String reason;
    @ExcelProperty("请假天数")
    private int daynum;
    @ExcelProperty("请假人学号")
    private String stuNo;
    @ExcelProperty("申请时间")
    private Date applytime;
    @ExcelProperty("请假单状态")
    private String status;
    @ExcelProperty("审核时间")
    private Date audittime;
    @ExcelProperty("导员意见")
    private String opinion;

    private Boolean Bo;
}
