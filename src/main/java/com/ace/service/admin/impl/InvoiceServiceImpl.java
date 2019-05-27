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

@Service("admin_invoice_service")
public class InvoiceServiceImpl implements InvoiceService {
    Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Resource
    private InvoiceMapper invoiceMapper;
    @Resource
    private OrderMapper orderMapper;

    @Override
    public DataTable<InvoiceOrder> dataTable(Staff staff, InvoiceCriteria criteria) {
        DataTable<InvoiceOrder> dataTable = new DataTable<>();
        dataTable.setStart(criteria.getStart());
        dataTable.setLength(criteria.getLength());
        dataTable.setRecordsFiltered(invoiceMapper.recordsTotal(staff, criteria));
        dataTable.setData(invoiceMapper.dataList(staff, criteria));
        return dataTable;
    }

    @Override
    public Invoice findById(String orderNo) {
        return invoiceMapper.findBy(orderNo);
    }

    @Override
    public void create(String orderNo, Invoice invoice) {
        Order order = orderMapper.findById(orderNo);
        if (order.getStatus() == OrderStatus.PAID || true) {
            invoice.setOrderId(order.getId());
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
        logger.info("邮寄");
        invoiceMapper.mail(invoice, InvoiceStatus.SHIPPED);
    }
}
