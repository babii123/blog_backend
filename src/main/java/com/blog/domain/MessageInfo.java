package com.blog.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassNameMessageInfo
 * @Description TODO
 * @Author DELL
 * @Date 2022/1/2114:36
 * @Version 1.0
 **/
@Data
public class MessageInfo {
    private String senderId;
    private String receiverId;
    private String userName;
    private String avatar;
    private String message;


}
