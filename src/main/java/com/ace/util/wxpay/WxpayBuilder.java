package com.ace.util.wxpay;


import com.ace.config.WxpayConfig;
import com.ace.entity.Order;
import com.ace.entity.Receipt;
import com.ace.entity.RefundApplication;
import com.ace.entity.Wxpay;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;

/**
 * @author john
 * @date 19-5-18 下午7:50
 */
@Log4j2
public enum WxpayBuilder {
    instance;
    private final String gateway = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private final String tradeIp = "58.34.201.235";
    private final String tradeType = "APP";
    private final String notifyUrl = "http://mrp.baobanwang.com/callback/wxpay";
    private final String refundGate = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    @Resource
    WxpayConfig config;

    public WxpayResponse getPay(WxpayConfig config, Order order) {
        WxPayClient client = new WxPayClient(gateway, config.getAppId(), config.getSeller(), config.getAppKey(), tradeIp, tradeType, notifyUrl);
        WxpayRequest request = new WxpayRequest(
                "爱包办-会议室预订-" + order.getOrderNo(),
                order.getOrderNo(),
                order.getPayAmount()
        );
        return client.makeRequest(request);
    }

    public boolean check(WxpayConfig config, SortedMap<String, Object> params, String sign) {
        try {
            WxPayClient client = new WxPayClient(gateway, config.getAppId(), config.getSeller(), config.getAppKey(), tradeIp, tradeType, notifyUrl);
            return client.signatureCheck(params, sign);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    public boolean refund(WxpayConfig config, String orderNo, BigDecimal totalAmt, BigDecimal refundAmt) {
        try {
            WxPayClient client = new WxPayClient(refundGate, config.getAppId(), config.getSeller(), config.getAppKey(), tradeIp, tradeType, notifyUrl);
            WxpayRefundRequest request = new WxpayRefundRequest(orderNo, totalAmt, refundAmt);
            return client.refund(request);
        } catch (Exception exp) {
            log.info("微信退款失败::" + exp.getMessage());
            return false;
        }
    }

}
