package com.blog.service;

import com.blog.domain.Type;

import java.util.List;

public interface TypeService {
    /**
     * 获取文章类型的分类信息
     * @return
     */
    public List<Type> getTypeList();
}
