package com.ace.util.wxpay;


import com.ace.entity.Order;
import com.ace.entity.Wxpay;

import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;

/**
 * @author john
 * @date 19-5-18 下午7:50
 */
public enum WxpayBuilder {
    instance;
    private final String gateway = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private final String tradeIp = "58.34.201.235";
    private final String tradeType = "APP";
    private final String notifyUrl = "http://open.aidong.me/callback/wx";


    public WxpayResponse getPay(Wxpay config, Order order) {
        WxPayClient client = new WxPayClient(gateway, config.getAppId(), config.getMchId(), config.getSecretKey(), tradeIp, tradeType, notifyUrl);
        WxpayRequest request = new WxpayRequest(
                "爱包办-会议室预订-" + order.getOrderNo(),
                order.getOrderNo(),
                order.getPayAmount()
        );
        return client.makeRequest(request);
    }

    public boolean signatureCheck(Wxpay config, SortedMap<String, Object> params, String sign) {
        try {
            WxPayClient client = new WxPayClient(gateway, config.getAppId(), config.getMchId(), config.getSecretKey(), tradeIp, tradeType, notifyUrl);
            return client.signatureCheck(params, sign);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }
}
