package com.ace.dao;

import com.ace.controller.admin.concerns.OperLogCriteria;
import com.ace.controller.admin.concerns.OrderCriteria;
import com.ace.entity.OperLog;
import com.ace.entity.Order;
import com.ace.entity.Staff;
import com.ace.entity.Support;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OperLogMapper {
    Long recordsTotal(@Param("criteria") OperLogCriteria criteria);

    List<OperLog> dataList(@Param("criteria") OperLogCriteria criteria);

    void create(OperLog log);

}
