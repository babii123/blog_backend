package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.domain.Article;
import com.blog.vo.*;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Override
    public boolean addArticle(Article article) {
        return articleDao.addArticle(article);
    }

    @Override
    public boolean deleteArticle(String id) {
        return articleDao.deleteArticle(id);
    }

    @Override
    public ArticleEditVo getArticle(String id) {
        return articleDao.getArticle(id);
    }

    @Override
    public boolean updateArticle(Article article) {
        return articleDao.updateArticle(article);
    }

    @Override
    public ArticleDetailVO getArticleById(String id) {
        return articleDao.getArticleById(id);
    }

    @Override
    public List<ArticleInfoVO> getArticleList(Integer page, Integer pageSize) {
        return articleDao.getArticleList(page,pageSize);
    }

    @Override
    public List<ArticleInfoVO> getArticleListById(String typeId, Integer page, Integer pageSise) {
        return articleDao.getArticleListByTypeId(typeId,page,pageSise);
    }

    @Override
    public Integer getCountAll() {
        return articleDao.getCountAll();
    }

    @Override
    public Integer getCountAllById(String typeId) {
        return articleDao.getCountById(typeId);
    }

    @Override
    public Integer getBeViewedByFramerId(String id) {
        return articleDao.getBeViewedByFramerId(id);
    }

    @Override
    public Integer getBeLikedByFramerId(String id) {
        return articleDao.getBeLikedByFramerId(id);
    }

    @Override
    public List<ArticleInfoVO> getArticleListByFramerId(String framer_id) {
        return articleDao.getArticleListByFramerId(framer_id);
    }

    @Override
    public ArticleCountVO getCountAllByType() {
        return articleDao.getCountAllByType();
    }

    @Override
    public ArticleCountVO getCountAllByFramerId(String framerId) {
        return articleDao.getCountAllByFramerId(framerId);
    }
}
