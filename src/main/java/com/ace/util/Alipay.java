package com.ace.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import static com.alipay.api.AlipayConstants.CHARSET;

/**
 * @author john
 * @date 19-5-18 下午7:39
 */
public enum Alipay {
    Instance;
    private final String getway = "https://mobile.securitypay.pay";
    private final String appId = "";
    private final String privateKey = "";
    private final String publicKey = "";
    private final String format = "json";
    private final String signType = "RSA2";
    private final AlipayClient client;
    private final String notifyUrl = "";

    Alipay() {
        client = new DefaultAlipayClient(getway, appId, privateKey, format, CHARSET, publicKey, signType);
    }

    public String getPay(AlipayTradeAppPayModel payModel) throws AlipayApiException {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setBizModel(payModel);
        request.setNotifyUrl(notifyUrl);
        AlipayTradeAppPayResponse response = client.sdkExecute(request);
        return response.getBody();
    }
}
