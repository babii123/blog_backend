package com.blog.service;

public interface CollectService {
    /**
     * 获取指定用户的收藏集的数量
     * @param id
     * @return
     */
    public Integer getCollectionSetByFramerId(String id);

    /**
     * 用户是否收藏指定文章
     * @param framerId
     * @param articleId
     * @return
     */
    public boolean isCollect(String framerId,String articleId);

    /**
     * 点击收藏
     * @param framerId
     * @param articleId
     * @return
     */
    public boolean collectArticle(String framerId,String articleId);
}
