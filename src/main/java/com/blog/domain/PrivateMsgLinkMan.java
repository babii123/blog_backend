package com.blog.domain;

import lombok.Data;

@Data
public class PrivateMsgLinkMan {
    private String userId;
    private String friendId;
    private String userName;
    private String avatar;
    private Integer conStatus;
    private Integer needInit;
    private Integer placeTop;
}
