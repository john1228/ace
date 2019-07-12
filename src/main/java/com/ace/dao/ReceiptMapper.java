package com.ace.dao;

import com.ace.controller.admin.concerns.ReceiptCriteria;
import com.ace.entity.Receipt;
import com.ace.entity.ReceiptDetail;
import com.ace.entity.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ReceiptMapper {
    List<ReceiptDetail> dataList(@Param("staff") Staff staff, @Param("criteria") ReceiptCriteria criteria);

    Long recordsTotal(@Param("staff") Staff staff, @Param("criteria") ReceiptCriteria criteria);

    void create(Receipt receipt);

    Receipt findById(@Param("id") Integer id);
}
