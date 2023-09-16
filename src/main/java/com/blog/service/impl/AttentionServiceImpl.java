package com.blog.service.impl;

import com.blog.dao.AttentionDao;
import com.blog.vo.FramerAttentionVO;
import com.blog.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttentionServiceImpl implements AttentionService {
    @Autowired
    AttentionDao attentionDao;

    @Override
    public boolean isAttention(String framerI, String framerP) {
        return attentionDao.isAttention(framerI, framerP);
    }

    @Override
    public Integer getFollowCountByFramerId(String id) {
        return attentionDao.getFollowCountByFramerId(id);
    }

    @Override
    public Integer getBeFollowCountByFramerId(String id) {
        return attentionDao.getBeFollowCountByFramerId(id);
    }

    @Override
    public List<FramerAttentionVO> getFollowListByFramerId(String framer_id) {
        return attentionDao.getFollowListByFramerId(framer_id);
    }

    @Override
    public List<FramerAttentionVO> getBeFollowListByFramerId(String framer_id) {
        return attentionDao.getBeFollowListByFramerId(framer_id);
    }

    @Override
    public boolean followFramer(String FramerI, String FramerP) {
        return attentionDao.followFramer(FramerI, FramerP);
    }
}
