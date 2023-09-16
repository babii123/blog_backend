package com.blog.service.impl;

import com.blog.dao.FramerDao;
import com.blog.domain.Framer;
import com.blog.vo.FramerEditVo;
import com.blog.vo.FramerInfoVO;
import com.blog.vo.FramerWorkDataStatisticsVO;
import com.blog.service.FramerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FramerServiceImpl implements FramerService {
    @Autowired
    private FramerDao framerDao;

    @Override
    public boolean addFramer(Framer framer) {
        return false;
    }

    @Override
    public boolean framerLogin(String username, String password) {
        return false;
    }

    @Override
    public boolean deleteFramerById() {
        return false;
    }

    @Override
    public List<Framer> getFramerList() {
        return framerDao.getFramerList();
    }

    @Override
    public FramerWorkDataStatisticsVO getStatistics(String id) {
        return framerDao.getStatistics(id);
    }

    @Override
    public FramerInfoVO getCenterInfo(String id) {
        return framerDao.getFramerCenterInfo(id);
    }

    @Override
    public FramerEditVo getFramerEditInfo(String framerId) {
        return framerDao.getFramerEditInfo(framerId);
    }

    @Override
    public boolean updateFramerEditInfo(FramerEditVo editVo) {
        return framerDao.updateFramerEditInfo(editVo);
    }


}
