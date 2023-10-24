package com.exam.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Leave {
    private String leaveID;
    private String courseID;
    private String reason;
    private int daynum;
    private String stuNo;
    private Date applytime;
    private String status;
    private Date audittime;
    private String opinion;
}
