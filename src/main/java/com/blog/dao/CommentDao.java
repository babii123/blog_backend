package com.blog.dao;

import com.blog.domain.Comment;
import com.blog.domain.CommentRecord;
import com.blog.vo.CommentListVO;
import com.blog.vo.CommentSecondVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDao {
    /**
     * 评论文章
     *
     * @param comment
     */
    @Insert("INSERT INTO `comment`(articleId,framerId,content,commentDate,topCommentId,replyCommentId) VALUES(#{articleId},#{framerId},#{content},NOW(),0,0);")
    @Options(useGeneratedKeys = true, keyProperty = "commentId", keyColumn = "id")
    void commentArticle(Comment comment);

    /**
     * 存评论操作记录
     * @param commentRecord
     * @return
     */
    @Insert("INSERT INTO comment_record VALUES(#{framerId},#{commentId},#{action},NOW(),0)")
    boolean insertCommentRecord(CommentRecord commentRecord);

    /**
     * 回复评论
     *
     * @param comment
     */
    @Insert("INSERT INTO `comment`(articleId,framerId,content,replyCommentId,commentDate,topCommentId) VALUES(#{articleId},#{framerId},#{content},#{replyCommentId},NOW(),#{topCommentId});")
    @Options(useGeneratedKeys = true, keyProperty = "commentId", keyColumn = "id")
    void replyComment(Comment comment);

    @Select("SELECT c.id as id, f.id as framerId , f.`name`as framerName,f.img_path as framerAvater, " +
            "c.content as content, commentDate, beReplyCount,  beLikeCount " +
            "FROM `comment` AS c LEFT JOIN framer AS f ON c.framerId = f.id " +
            "WHERE articleId = #{articleId} AND replyCommentId = 0 AND status IN (1,2)")
    @Results(id="getFirstFloor",value={
            @Result(id = true,property = "commentId",column = "id"),
            @Result(property = "framerId",column = "framerId"),
            @Result(property = "framerName",column = "framerName"),
            @Result(property = "framerAvatar",column = "framerAvater"),
            @Result(property = "content",column = "content"),
            @Result(property = "commentDate",column = "commentDate"),
            @Result(property = "beReplyCount",column = "beReplyCount"),
            @Result(property = "beLikeCount",column = "beLikeCount"),
            @Result(property = "items",column = "id",javaType = List.class,
            many = @Many(select = "com.blog.dao.CommentDao.getSecondFloor")),
    })
    List<CommentListVO> getFirstFloor(@Param("articleId") String articleId);


    @Select("SELECT c1.id AS commentId,c1.framerId as framerId, c1.framerName AS framerName,c1.framerAvatar AS framerAvatar,c1.content AS content,\n" +
            "c1.commentDate AS commentDate,c2.content AS replyCommentContent,c2.framerId as replyFramerId,c2.framerName AS replyFramerName,\n" +
            "c1.beReplyCount AS beReplyCount,c1.beLikeCount AS beLikeCount \n" +
            "FROM \n" +
            "(SELECT `comment`.id AS id, framerId,framer.`name` AS framerName, framer.img_path AS framerAvatar, `comment`.content AS content,commentDate,beReplyCount,beLikeCount, replyCommentId, topCommentId,`comment`.`status` as status\n" +
            "from `comment` LEFT JOIN framer ON `comment`.framerId = framer.id) AS c1 \n" +
            "JOIN \n" +
            "(SELECT `comment`.id AS id, framerId,framer.`name` AS framerName, framer.img_path AS framerAvatar, `comment`.content AS content,commentDate from `comment` LEFT JOIN framer ON `comment`.framerId = framer.id) AS c2 \n" +
            "ON c1.replyCommentId = c2.id\n" +
            "WHERE c1.topCommentId = #{topCommentId} AND c1.`status` IN (1,2)")

    public abstract List<CommentSecondVO> getSecondFloor(@Param("topCommentId") Integer topCommentId);

    /**
     * 回复评论后，被回复的评论 beReplyCount+1
     * @param replyCommentId
     * @return
     */
    @Update("UPDATE `comment` SET beReplyCount = beReplyCount +1 WHERE id = #{replyCommentId}")
    boolean updateBeReply(@Param("replyCommentId") Integer replyCommentId);

    /**
     * 回复评论后，被回复的评论 beReplyCount+1
     * @param replyCommentId
     * @return
     */
    @Update("UPDATE `comment` SET beLikeCount = beLikeCount +1 WHERE id = #{replyCommentId}")
    boolean updateBeLike(@Param("replyCommentId") Integer replyCommentId);

}
