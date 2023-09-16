package com.blog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Framer {
    private String id;
    private String name;
    private String img_path;
    private String introduce;
    private Date registerTime;
    private String password;
    private String email;
    private String phone;
    private Date recentTime;
}
