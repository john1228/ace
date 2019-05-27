package com.ace.dao;

import com.ace.controller.admin.concerns.InvoiceCriteria;
import com.ace.entity.Invoice;
import com.ace.entity.InvoiceOrder;
import com.ace.entity.Staff;
import com.ace.entity.concern.invoice.InvoiceStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface InvoiceMapper {
    List<InvoiceOrder> dataList(@Param("staff") Staff staff, @Param("criteria") InvoiceCriteria criteria);

    Long recordsTotal(@Param("staff") Staff staff, @Param("criteria") InvoiceCriteria criteria);

    void create(Invoice invoice);

    InvoiceOrder findOrder(@Param("orderNo") String orderNo);

    Invoice findBy(@Param("orderNo") String orderNo);

    void update(Invoice invoice);

    void mail(@Param("invoice") Invoice invoice, @Param("status") InvoiceStatus status);
}
