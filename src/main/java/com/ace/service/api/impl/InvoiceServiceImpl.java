package com.ace.service.api.impl;

import com.ace.dao.InvoiceMapper;
import com.ace.dao.OrderMapper;
import com.ace.entity.Account;
import com.ace.entity.Invoice;
import com.ace.entity.Order;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.service.api.InvoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author john
 * @date 19-5-14 下午3:34
 */

@Service("api-invoice-service")
public class InvoiceServiceImpl extends BaseService implements InvoiceService {
    @Resource
    private InvoiceMapper invoiceMapper;
    @Resource
    private OrderMapper orderMapper;

    @Override
    public void create(Account account, String orderNo, Invoice invoice) {
        Invoice invoiced = invoiceMapper.findBy(orderNo);
        if (invoiced == null) {
            Order order = orderMapper.findById(orderNo);
            if (order == null) {
                account.setErrMsg("无效的订单");
            } else {
                if (order.getStatus() == OrderStatus.PAID || true) {
                    invoice.setOrderId(order.getId());
                    invoiceMapper.create(invoice);
                } else {
                    account.setErrMsg("当前订单状态:" + order.getStatus().getName() + ";不能开发票");
                }
            }
        } else {
            invoiceMapper.update(invoice);
        }
    }

    @Override
    public Invoice show(String orderNo) {
        return invoiceMapper.findBy(orderNo);
    }
}
