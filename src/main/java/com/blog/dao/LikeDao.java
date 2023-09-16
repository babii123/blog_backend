package com.blog.dao;

import com.blog.vo.ArticleInfoVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LikeDao {
    String getLikeListByFramerId_sql = "SELECT article.id as id," +
            "article.framer_id as framerId," +
            "article.title as title," +
            "article.introduce as introduce," +
            "DATE_FORMAT(article.updateTime,'%Y-%m-%d %H:%i:%s') as updateTime," +
            "type.typeName as typeName," +
            "framer.`name` as framerName,"+
            "article.view_count as viewCount," +
            "article.like_count as likeCount," +
            "article.comment_count as commentCount " +
            "FROM article inner join type on type.id = article.type_id inner join framer on framer.id = article.framer_id " +
            "where article.id in (select article_id from `like` where framer_id = #{framerId})";
    @Select(getLikeListByFramerId_sql)
    public List<ArticleInfoVO> getLikeListByFramerId(@Param("framerId") String framer_id);

    /**
     * 判断点赞关系
     * @param framerId
     * @param articleId
     * @return
     */
    @Select("select count(like_time) from `like` where framer_id = #{framer_id} and article_id = #{article_id} and is_del = 1")
    public boolean isLike(@Param("framer_id") String framerId, @Param("article_id") String articleId);

    /**
     * 点赞操作
     * @param framerId
     * @param articleId
     * @return
     */
    @Insert("insert into `like` (framer_id, article_id, like_time,is_del) values (#{framer_id},#{article_id},NOW(),1) \n" +
            "on duplicate key update is_del = abs(is_del -1)")
    public boolean likeArticle(@Param("framer_id") String framerId, @Param("article_id") String articleId);
}
