package com.blog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class CommentRecord {
    private String framerId;
    private Integer commentId;
    private Integer action;
    private Date actionDate;
    private Integer status;
}
