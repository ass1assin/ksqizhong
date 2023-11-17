package com.exam.entity;

import lombok.Data;

@Data
public class Course {
    private String courseID;
    private String classID;
    private String className;
    private String courseName;
    private String year;
    private String term;
    private int hour;
}
