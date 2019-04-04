package com.ace.dao;

import com.ace.entity.room.Attribute;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AttributeMapper {
    List<Attribute> dataList(@Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Integer recordsTotal(@Param("keyword") String keyword);

    void create(Attribute attribute);

    Attribute findById(@Param("id") int id);

    void update(Attribute attribute);

    void destroy(int id);
}
