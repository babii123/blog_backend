package com.blog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer commentId;
    private String articleId;
    private String framerId;
    private String content;
    private Date commentDate;
    private String beLikeCount;
    private Integer beReplyCount;
    // 顶层评论 的 id，如果是 顶层评论 topCommentId 置0
    private Integer topCommentId;
    private Integer replyCommentId;
    private Integer status;
}
