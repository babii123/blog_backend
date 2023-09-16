package com.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ArticleDetailVO {
    private String id;
    private String title;
    private String framerId;
    private String framerName;
    private String framerAvatar;
    private String content;
    @JsonFormat(pattern="yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy年MM月dd日 HH:mm:ss")
    private Date updateTime;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private Integer collectCount;
}
