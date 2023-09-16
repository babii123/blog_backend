package com.blog.service;

import com.blog.vo.ArticleInfoVO;

import java.util.List;

public interface LikeService {
    /**
     * 获取指定用户的 点赞文章列表
     * @return
     */
    public List<ArticleInfoVO> getLikeListByFramerId(String framer_id);

    /**
     * 判断登录的用户是否点赞指定文章
     * @param framer_id
     * @param article_id
     */
    public boolean isLike(String framer_id,String article_id);

    /**
     * 点赞文章
     * @param framer_id
     * @param article_id
     * @return
     */
    public boolean likeArticle(String framer_id,String article_id);
}
