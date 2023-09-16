package com.blog.vo;

import lombok.Data;

import java.util.Date;

/**
 * 返回联系人列表的数据类型
 */
@Data
public class LinkManVO {
    private String friendId;
    private String userName;
    private String avatar;
    private Integer needInit;
    // 为了显示灰色背景
    private Integer placeTop;
    private Date recentTime;
    private String recentMsg;
    private Integer unreadCount;
}
