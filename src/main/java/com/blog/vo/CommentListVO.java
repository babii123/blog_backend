package com.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 封装返回给每篇文章的评论列表
 */
@Data
public class CommentListVO {
    private Integer commentId;
    private String framerId;
    private String framerName;
    private String framerAvatar;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date commentDate;
    private String beReplyCount;
    private String beLikeCount;
    // 是否被指定用户点赞
    private boolean beLike;
    private List<CommentSecondVO> items;
}
