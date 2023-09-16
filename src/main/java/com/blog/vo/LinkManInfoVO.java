package com.blog.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LinkManInfoVO {
    private String friendId;
    private String userName;
    private String avatar;
    private boolean attention;
    private Integer needInit;
    private Integer placeTop;
}