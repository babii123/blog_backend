package com.blog.service;

import com.blog.domain.Article;
import com.blog.vo.*;

import java.util.List;

public interface ArticleService {
    /**
     * 新增文章
     * @param article
     * @return
     */
    public boolean addArticle(Article article);

    /**
     * 删除指定文章
     * @param id
     */
    public boolean deleteArticle(String id);

    /**
     * 获取文章需要编辑的博客信息
     * @param id
     * @return
     */
    public ArticleEditVo getArticle(String id);


    /**
     * 更新文章
     * @param article
     * @return
     */
    public boolean updateArticle(Article article);

    /**
     * 通过Id获取文章信息
     * @param id
     * @return
     */
    public ArticleDetailVO getArticleById(String id);

    /**
     * 获取所有文章列表，分页查询
     * @return
     */
    public List<ArticleInfoVO> getArticleList(Integer page, Integer pageSise);

    /**
     * 获取文章列表通过Id，并分页查询
     * @return
     */
    public List<ArticleInfoVO> getArticleListById(String typeId,Integer page, Integer pageSise);

    /**
     * 获取所有文章总数
     * @return
     */
    public Integer getCountAll();

    /**
     * 获取指定ID文章总数
     * @return
     */
    public Integer getCountAllById(String typeId);

    /**
     * 获取指定用户文章被阅读的数量
     * @param id
     * @return
     */
    public Integer getBeViewedByFramerId(String id);

    /**
     * 获取指定用户文章被点赞的数量
     * @param id
     * @return
     */
    public Integer getBeLikedByFramerId(String id);

    /**
     * 个人中心获取作者 的文章列表
     * @param framer_id
     * @return
     */
    public List<ArticleInfoVO> getArticleListByFramerId(String framer_id);

    /**
     * 统计各个类型文章的数目
     * @return
     */
    public ArticleCountVO getCountAllByType();

    /**
     * 统计各个类型文章的数目,通过id
     * @return
     */
    public ArticleCountVO getCountAllByFramerId(String framerId);
}
