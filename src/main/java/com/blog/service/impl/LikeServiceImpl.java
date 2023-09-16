package com.blog.service.impl;

import com.blog.dao.LikeDao;
import com.blog.vo.ArticleInfoVO;
import com.blog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeDao likeDao;

    @Override
    public List<ArticleInfoVO> getLikeListByFramerId(String framer_id) {
        return likeDao.getLikeListByFramerId(framer_id);
    }

    @Override
    public boolean isLike(String framer_id, String article_id) {
        return likeDao.isLike(framer_id,article_id);
    }

    @Override
    public boolean likeArticle(String framer_id, String article_id) {
        return likeDao.likeArticle(framer_id, article_id);
    }
}
