package com.exam.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Leave {
    private String leaveID;
    private String courseID;
    private String reason;
    private int dayNum;
    private String stuNo;
    private Date applyTime;
    private String status;
    private Date auditTime;
    private String opinion;

    private Boolean Bo;
}
