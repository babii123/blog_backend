package com.blog.service.impl;

import com.blog.dao.FramerDao;
import com.blog.domain.Framer;
import com.blog.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    FramerDao framerDao;


    @Override
    public boolean register(Framer framer) {
        return framerDao.addFramer(framer);
    }
}
