package com.ace.service.admin.impl;

import com.ace.config.AlipayConfig;
import com.ace.config.WxpayConfig;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.RefundCriteria;
import com.ace.dao.*;
import com.ace.entity.*;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.service.admin.RefundService;
import com.ace.util.AlipayBuilder;
import com.ace.util.wxpay.WxpayBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author john
 * @date 19-8-8 下午12:20
 */
@Log4j2
@Service("admin-refund-service")
public class RefundServiceImpl implements RefundService {

    @Resource
    private RefundMapper refundMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private ReceiptMapper receiptMapper;
    @Resource
    private AlipayConfig alipayConfig;
    @Resource
    private WxpayConfig wxpayConfig;

    @Override
    public void dataTable(Staff staff, RefundCriteria criteria, DataTable<RefundApplication> dataTable) {
        dataTable.setRecordsFiltered(refundMapper.recordsTotal(staff, criteria));
        dataTable.setData(refundMapper.dataList(staff, criteria, dataTable.getStart(), dataTable.getLength()));
    }

    @Override
    @Transactional
    public void agree(Staff staff, Long id, BigDecimal confirmAmt) {

        Order order = refundMapper.refundOrder(id);
        refundMapper.agree(staff, id, confirmAmt);
        orderMapper.update(order.getOrderNo(), OrderStatus.REFUNDED);
        //第三方流水处理
        ReceiptDetail receipt = receiptMapper.findByOrderNo(order.getOrderNo());
        if (receipt.getNo().startsWith("2")) {
            //支付宝退款处理
            AlipayBuilder.instance.refund(alipayConfig, order.getOrderNo(), confirmAmt, String.valueOf(System.currentTimeMillis()));
        } else {
            //微信退款处理
            log.info("微信退款");
            WxpayBuilder.instance.refund(wxpayConfig, order.getOrderNo(), confirmAmt, receipt.getPayAmount());
        }

    }

    @Override
    public void reject(Staff staff, Long id) {
        refundMapper.reject(staff, id);
    }
}
