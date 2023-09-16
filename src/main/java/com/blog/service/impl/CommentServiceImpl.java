package com.blog.service.impl;

import com.blog.dao.CommentDao;
import com.blog.domain.Comment;
import com.blog.domain.CommentRecord;
import com.blog.service.CommentService;
import com.blog.vo.CommentListVO;
import com.blog.vo.CommentSecondVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Override
    public void commentArticle(Comment comment) {
        commentDao.commentArticle(comment);
    }

    @Override
    public boolean insertCommentRecord(CommentRecord commentRecord) {
        return commentDao.insertCommentRecord(commentRecord);
    }

    @Override
    public void replyComment(Comment comment) {
//        回复评论
        commentDao.replyComment(comment);
//        回复评论后，被回复的评论 beReplyCount+1
        commentDao.updateBeReply(comment.getReplyCommentId());
    }

    /**
     * 点赞评论
     * @param commentRecord
     * @return
     */
    @Override
    public void likeComment(CommentRecord commentRecord) {
        commentDao.insertCommentRecord(commentRecord);
        // 点赞后beLikeCount+1
        commentDao.updateBeLike(commentRecord.getCommentId());
    }

    /**
     * 获取指定文章的评论区
     * @param articleId
     * @return
     */
    @Override
    public List<CommentListVO> getCommentByArticleId(String articleId) {
        List<CommentListVO> listVOS = commentDao.getFirstFloor(articleId);
        System.out.println("listVOS: "+ listVOS.toString());
        List<CommentSecondVO> commentSecondVOList = commentDao.getSecondFloor(8);
        System.out.println("secondList: "+commentSecondVOList.toString());
        return listVOS;
    }
}
