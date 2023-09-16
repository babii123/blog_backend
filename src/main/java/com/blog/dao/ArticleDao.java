package com.blog.dao;

import com.blog.domain.Article;
import com.blog.vo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleDao {
    String getArticleList_Sql = "SELECT article.id as id," +
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
            "limit 1,10";
    String getArticleListByTypeId_Sql = "SELECT article.id as id," +
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
            "where article.type_id = #{typeId} "+
            "limit #{page},#{pageSize}";
    String getArticleById_sql = "SELECT article.id as id," +
            "article.title as title," +
            "framer.id as framerId,"+
            "framer.`name` as framerName,"+
            "framer.img_path as framerAvatar,"+
            "article.article_content as content," +
            "DATE_FORMAT(article.updateTime,'%Y-%m-%d %H:%i:%s') as updateTime," +
            "article.view_count as viewCount," +
            "article.like_count as likeCount," +
            "article.collect_count as collectCount,"+
            "article.comment_count as commentCount "+
            "from article inner join framer on framer.id = article.framer_id " +
            "WHERE article.id=#{id}";
    String getArticleListByFramerId_Sql = "SELECT article.id as id," +
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
            "where article.framer_id = #{framerId} ";

    /**
     * 发布文章
     * @param article
     * @return
     */
    @Insert("insert into article(id,type_id,framer_id,title,article_content,introduce,addTime,updateTime) values(#{id},#{typeId},#{framerId},#{title},#{articleContent},#{introduce},NOW(),NOW())")
    public boolean addArticle(Article article);

    /**
     * 删除文章
     */
    @Delete("delete from article where id = #{id}")
    public boolean deleteArticle(String id);

    /**
     * 获取文章
     */
    @Select("select id,title,article_content as articleContent,type_id as typeId,introduce from article where id = #{id}")
    public ArticleEditVo getArticle(String id);

    /**
     * 修改文章
     * @param article
     */
    @Update("update article set type_id=#{typeId},title=#{title},article_content=#{articleContent},introduce=#{introduce},updateTime=NOW() where id = #{id}")
    public boolean updateArticle(Article article);

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @Select(getArticleById_sql)
    public ArticleDetailVO getArticleById(@Param("id") String id);

    /**
     * 获取文章列表，用于首页
     * @param page
     * @param pageSize
     * @return
     */
    @Select(getArticleList_Sql)
    public List<ArticleInfoVO> getArticleList(@Param("page") Integer page, @Param("pageSize") Integer pageSize);

    /**
     * 获取文章列表用于分类
     * @param type_id
     * @param page
     * @param pageSize
     * @return
     */
    @Select(getArticleListByTypeId_Sql)
    public List<ArticleInfoVO> getArticleListByTypeId(@Param("typeId") String type_id,@Param("page") Integer page, @Param("pageSize") Integer pageSize);

    /**
     * 获取所有文章的数目
     * @return
     */
    @Select("select count(id) as count from article")
    public Integer getCountAll();

    /**
     * 获取指定类型的文章总数
     * @param id
     * @return
     */
    @Select("select count(id) as count from article where type_id = #{id}")
    public Integer getCountById(@Param("id") String id);

    /**
     * 获取指定用户的被阅读数
     * @param id
     * @return
     */
    @Select("select ifnull(sum(view_count),0) as `count` from article where framer_id = #{id}")
    public Integer getBeViewedByFramerId(String id);

    /**
     * 获取指定用户被点赞数
     * @param id
     * @return
     */
    @Select("select ifnull(sum(like_count),0) as `count` from article where framer_id = #{id}")
    public Integer getBeLikedByFramerId(String id);

    /**
     * 个人中心，展示指定用户编写文章的列表
     * @param framer_id
     * @return
     */
    @Select(getArticleListByFramerId_Sql)
    public List<ArticleInfoVO> getArticleListByFramerId(@Param("framerId") String framer_id);

    /**
     * 获取不同类别文章的数量，用于统计
     * @return
     */
    @Select("select IFNULL(SUM(CASE WHEN type_id = '6809637773935378440' THEN 1 ELSE 0 END),0) AS ai,IFNULL(SUM(CASE WHEN type_id = '6809637772874219534' THEN 1 ELSE 0 END),0) AS article,IFNULL(SUM(CASE WHEN type_id = '6809637771511070734' THEN 1 ELSE 0 END),0) AS freebie,IFNULL(SUM(CASE WHEN type_id = '6809637769959178254' THEN 1 ELSE 0 END),0) AS backend,IFNULL(SUM(CASE WHEN type_id = '6809637767543259144' THEN 1 ELSE 0 END),0) AS frontend,IFNULL(SUM(CASE WHEN type_id = '6809635626879549454' THEN 1 ELSE 0 END),0) AS android,IFNULL(SUM(CASE WHEN type_id = '6809635626661445640' THEN 1 ELSE 0 END),0) AS ios\n" +
            "from article")
    public ArticleCountVO getCountAllByType();

    /**
     * 获取指定用户发布文章的类型数量
     * @param framer_id
     * @return
     */
    @Select("select IFNULL(SUM(CASE WHEN type_id = '6809637773935378440' THEN 1 ELSE 0 END),0) AS ai,IFNULL(SUM(CASE WHEN type_id = '6809637772874219534' THEN 1 ELSE 0 END),0) AS article,IFNULL(SUM(CASE WHEN type_id = '6809637771511070734' THEN 1 ELSE 0 END),0) AS freebie,IFNULL(SUM(CASE WHEN type_id = '6809637769959178254' THEN 1 ELSE 0 END),0) AS backend,IFNULL(SUM(CASE WHEN type_id = '6809637767543259144' THEN 1 ELSE 0 END),0) AS frontend,IFNULL(SUM(CASE WHEN type_id = '6809635626879549454' THEN 1 ELSE 0 END),0) AS android,IFNULL(SUM(CASE WHEN type_id = '6809635626661445640' THEN 1 ELSE 0 END),0) AS ios\n" +
            "from article where framer_id = #{framer_id}")
    public ArticleCountVO getCountAllByFramerId(String framer_id);
}
