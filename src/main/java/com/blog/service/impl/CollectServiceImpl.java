package com.blog.service.impl;

import com.blog.dao.CollectDao;
import com.blog.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectDao collectDao;

    @Override
    public Integer getCollectionSetByFramerId(String id) {
        return collectDao.getCollectionSetByFramerId(id);
    }

    @Override
    public boolean isCollect(String framerId, String articleId) {
        return collectDao.isCollect(framerId,articleId);
    }

    @Override
    public boolean collectArticle(String framerId, String articleId) {
        return collectDao.collectArticle(framerId, articleId);
    }
}
