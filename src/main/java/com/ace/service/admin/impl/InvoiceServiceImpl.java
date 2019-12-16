package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.InvoiceCriteria;
import com.ace.dao.InvoiceMapper;
import com.ace.dao.OrderMapper;
import com.ace.entity.Invoice;
import com.ace.entity.InvoiceOrder;
import com.ace.entity.Order;
import com.ace.entity.Staff;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.entity.concern.invoice.InvoiceStatus;
import com.ace.service.admin.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 发票管理
 */
@Service("admin_invoice_service")
public class InvoiceServiceImpl implements InvoiceService {
    Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Resource
    private InvoiceMapper invoiceMapper;
    @Resource
    private OrderMapper orderMapper;

    @Override
    public void dataTable(Staff staff, InvoiceCriteria criteria, DataTable<InvoiceOrder> dataTable) {
        dataTable.setRecordsFiltered(invoiceMapper.recordsTotal(staff, criteria));
        dataTable.setData(invoiceMapper.dataList(staff, criteria));
    }

    @Override
    public Invoice findByOrderNo(String orderNo) {
        return invoiceMapper.findBy(orderNo);
    }

    @Override
    public void create(String orderNo, Invoice invoice) {
        Order order = orderMapper.findByOrderNo(orderNo);
        if (order.getStatus() == OrderStatus.PAIDANDCONFIRM) {
            invoice.setOrderId(order.getId());
            invoice.setStatus(InvoiceStatus.INVOICED);
            invoiceMapper.create(invoice);
        }
    }

    @Override
    public void update(Invoice invoice) {
        invoiceMapper.update(invoice);
    }

    @Override
    public InvoiceOrder findOrder(String orderNo) {
        return invoiceMapper.findOrder(orderNo);
    }

    @Override
    public void mail(String orderNo, Invoice invoice) {
        Invoice was = invoiceMapper.findBy(orderNo);
        was.setExpress(invoice.getExpress());
        invoiceMapper.mail(was, InvoiceStatus.SHIPPED);
    }
}
