package com.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 个人中心页面，数据实体类
 */
@Data
public class FramerInfoVO {
    private String framerName;
    private String framerAvatar;
    private String introduce;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date registerTime;
    private Integer beViewed;
    private Integer beLiked;
    private Integer followCount;
    private Integer beFollowCount;
    private Integer collectionSet;
}
