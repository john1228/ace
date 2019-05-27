package com.ace.dao;

import com.ace.entity.OrderSupport;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OrderSupportMapper {
    void create(@Param("supports") List<OrderSupport> supports);

    List<OrderSupport> supportList(@Param("order") Long order);
}
