package com.ace.dao;

import com.ace.entity.Receipt;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ReceiptMapper {
    List<Receipt> dataList(@Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Integer recordsTotal(@Param("keyword") String keyword);

    void create(Receipt receipt);

    Receipt findById(@Param("id") Integer id);
}
