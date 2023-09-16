package com.blog.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryChatVO {
    private Integer id;
    private String senderId;
    private String receiverId;
    private Integer messageType;
    private String messageContent;
    private Date sendTime;
    private Integer status;
}
