package com.blog.dao;

import com.blog.domain.Type;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TypeDao {
    @Select("select id,typeName,type.key as 'key' from type")
    public List<Type> getTypeList();
}
