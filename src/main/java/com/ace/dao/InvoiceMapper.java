package com.ace.dao;

import com.ace.entity.Invoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface InvoiceMapper {
    List<Invoice> dataList(@Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Integer recordsTotal(@Param("keyword") String keyword);

    void create(Invoice invoice);

    Invoice findById(@Param("id") int id);

    void update(Invoice invoice);
}
