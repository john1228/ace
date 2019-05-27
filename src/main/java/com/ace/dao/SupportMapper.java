package com.ace.dao;

import com.ace.entity.Staff;
import com.ace.entity.Support;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SupportMapper {
    List<Support> dataList(@Param("staff") Staff staff, @Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Long recordsTotal(@Param("staff") Staff staff, @Param("keyword") String keyword);

    void create(Support support);

    Support findById(@Param("id") int id);

    void update(Support support);

    void destroy(int id);

    List<Support> all(@Param("staff") Staff staff);
}
