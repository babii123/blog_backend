package com.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 封装返回前端的数据类型，用于首页以及分类页面展示文章小卡片，列表
 */
@Data
public class ArticleInfoVO {
    private String id;
    private String typeName;
    private String framerId;
    private String framerName;
    private String title;
    private String introduce;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateTime;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
}
