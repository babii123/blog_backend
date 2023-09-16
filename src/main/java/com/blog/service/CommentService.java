package com.blog.service;

import com.blog.domain.Comment;
import com.blog.domain.CommentRecord;
import com.blog.vo.CommentListVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    /**
     * 用户直接评论文章，顶级评论
     * @param comment
     * @return
     */
    public void commentArticle(Comment comment);

    /**
     * 存评论记录
     * @param commentRecord
     * @return
     */
    boolean insertCommentRecord(CommentRecord commentRecord);

    /**
     * 回复评论
     * @param comment
     */
    void replyComment(Comment comment);

    /**
     * 点赞评论
     * @param commentRecord
     * @return
     */
    void likeComment(CommentRecord commentRecord);

    /**
     * 获取文章id
     * @param articleId
     * @return
     */
    List<CommentListVO> getCommentByArticleId(String articleId);
}
