package com.blog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CollectDao {
    @Select("select ifnull(count(id),0) as `count` from collect_gather where framer_id = #{id}")
    public Integer getCollectionSetByFramerId(String id);

    /**
     * 收藏关系
     * @param framerId
     * @param articleId
     * @return
     */
    @Select("select count(*) from `collect` where framer_id = #{framer_id} and article_id = #{article_id} and is_del = 1")
    public boolean isCollect(@Param("framer_id") String framerId, @Param("article_id") String articleId);

    /**
     * 收藏操作
     * @param framerId
     * @param articleId
     * @return
     */
    @Insert("insert into collect (framer_id, article_id, collect_time,is_del) values (#{framer_id},#{article_id},NOW(),1) \n" +
            "on duplicate key update is_del = abs(is_del -1)")
    public boolean collectArticle(@Param("framer_id") String framerId, @Param("article_id") String articleId);
}
