package com.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 评论列表第二层结构
 */
@Data
public class CommentSecondVO {
    private Integer commentId;
    private String framerId;
    private String framerName;
    private String framerAvatar;
    private String content;
    // 相隔多少天
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date commentDate;
    // 回复的评论内容
    private String replyCommentContent;
    // 回复的作者ID
    private String replyFramerId;
    //回复的作者名
    private String replyFramerName;
    private Integer beReplyCount;
    private Integer beLikeCount;
    // 是否被指定用户点赞
    private boolean beLike;
}
