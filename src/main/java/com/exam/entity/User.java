package com.exam.entity;
import lombok.Data;

@Data
public class User {
    private int id;
    private String fullname;
    private String password;
    private Integer telephone;
    private String type;

}
