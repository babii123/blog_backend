package com.blog.domain;

import lombok.Data;

import java.util.Date;

/**
 * 与数据库对应封装的实体类
 */
@Data
public class Article {
    private String id;
    private String typeId;
    private String framerId;
    private String title;
    private String articleContent;
    private String introduce;
    private Date addTime;
    private Date updateTime;
    private Integer viewCount;
    private Integer likeCount;
    private Integer collectCount;
    private Integer commentCount;

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", typeId='" + typeId + '\'' +
                ", framerId='" + framerId + '\'' +
                ", title='" + title + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", introduce='" + introduce + '\'' +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", collectCount=" + collectCount +
                ", commentCount=" + commentCount +
                '}';
    }
}
