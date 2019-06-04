package com.ace.util.wxpay;


import com.ace.entity.Order;

/**
 * @author john
 * @date 19-5-18 下午7:50
 */
public enum Wxpay {
    Instance;
    private final String getway = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private final String appId = "wx65004b49fd9e9a2d"; //"wx365ab323b9269d30";
    private final String mchId = "1527342701";//"1266307801";
    private final String secretKey = "9131012033275022XL31687403002649";
    private final String tradeIp = "58.34.201.235";
    private final String tradeType = "APP";
    private final String notifyUrl = "http://open.aidong.me/callback/wx";


    private final WxPayClient client;

    Wxpay() {
        client = new WxPayClient(getway, appId, mchId, secretKey, tradeIp, tradeType, notifyUrl);
    }

    public WxpayResponse getPay(Order order) {
        WxpayRequest request = new WxpayRequest(
                "爱包办-会议室预订-" + order.getOrderNo(),
                order.getOrderNo(),
                order.getPayAmount()
        );
        return client.makeRequest(request);
    }
}
