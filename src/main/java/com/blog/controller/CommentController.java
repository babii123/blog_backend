package com.blog.controller;

import com.blog.domain.Comment;
import com.blog.domain.CommentRecord;
import com.blog.service.CommentService;
import com.blog.vo.Code;
import com.blog.vo.Msg;
import com.blog.vo.Result;
import com.blog.vo.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 评论文章，设置顶级评论
     * @param map
     * @return
     */
    @PostMapping("/commentArticle")
    public Result commentArticle(@RequestBody Map map){
        String articleId = map.get("articleId").toString();
        String framerId = map.get("framerId").toString();
        String content = map.get("content").toString();
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setFramerId(framerId);
        comment.setContent(content);
        comment.setTopCommentId(0);

        System.out.println(articleId+framerId+content);

        // 存评论
        commentService.commentArticle(comment);
        Result result = new Result();
        result.setData(comment.getCommentId());
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);

        // 存评论记录
        CommentRecord commentRecord = new CommentRecord();
        commentRecord.setFramerId(framerId);
        commentRecord.setCommentId(comment.getCommentId());
        commentRecord.setAction(Action.COMMENT);
        // 未读 设 0
        commentService.insertCommentRecord(commentRecord);
        return result;
    }

    /**
     * 回复评论
     * @param map
     * @return
     */
    @PostMapping("/replyComment")
    public Result replyComment(@RequestBody Map map){
        String articleId = map.get("articleId").toString();
        String framerId = map.get("framerId").toString();
        String replyCommentId = map.get("replyCommentId").toString();
        String content = map.get("content").toString();
        String topCommentId = map.get("topCommentId").toString();

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setFramerId(framerId);
        comment.setReplyCommentId(Integer.parseInt(replyCommentId));
        comment.setContent(content);
        comment.setTopCommentId(Integer.parseInt(topCommentId));

        commentService.replyComment(comment);

        CommentRecord commentRecord = new CommentRecord();
        commentRecord.setFramerId(framerId);
        commentRecord.setCommentId(comment.getCommentId());
        commentRecord.setAction(Action.REPLY);
        commentService.insertCommentRecord(commentRecord);

        Result result = new Result();
        result.setData(comment.getCommentId());
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);

        return result;
    }

    /**
     * 点赞评论
     * @param framerId
     * @param commentId
     * @return
     */
    @GetMapping("/like/{framerId}/{commentId}")
    public Result likeComment(@PathVariable String framerId,@PathVariable String commentId) {

        CommentRecord commentRecord = new CommentRecord();
        commentRecord.setFramerId(framerId);
        commentRecord.setCommentId(Integer.parseInt(commentId));
        commentRecord.setAction(Action.LIKE);

        commentService.likeComment(commentRecord);
        Result result = new Result();
        result.setData(null);
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);

        return result;
    }

    /**
     * 返回评论结构
     * @param articleId
     * @return
     */
    @GetMapping("/{articleId}")
    public Result getCommentByArticleId(@PathVariable String articleId){
        Result result = new Result();
        result.setData(commentService.getCommentByArticleId(articleId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }
}
