package com.exam.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ExcelIgnoreUnannotated
public class Leave {
    @ExcelProperty(value = "请假单号")
    private String leaveID;
    @ExcelProperty(value ="课程ID")
    private String courseID;
    @ExcelProperty(value ="请假单原因")
    private String reason;
    @ExcelProperty(value ="请假天数")
    private Integer daynum;
    @ExcelProperty(value ="请假人学号")
    private String stuNo;
    @ExcelProperty(value ="申请时间")
    private Date applytime;
    @ExcelProperty(value ="请假单状态")
    private String status;
    @ExcelProperty(value ="审核时间")
    private Date audittime;
    @ExcelProperty(value ="导员意见")
    private String opinion;

    private Boolean Bo;
}
