package com.blog.service.impl;

import com.blog.dao.TypeDao;
import com.blog.domain.Type;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeDao typeDao;

    @Override
    public List<Type> getTypeList() {
        return typeDao.getTypeList();
    }
}
