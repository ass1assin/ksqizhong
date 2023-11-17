package com.exam.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;
@Data
//@ExcelIgnoreUnannotated
public class Leave {

    private String leaveID;

    private String courseID;

    private String courseName;

    private String reason;

    private Integer daynum;

    private String stuNo;

    private Date applytime;

    private String status;

    private Date audittime;

    private String opinion;

    private Boolean Bo;
}
