package com.ace.util;

import com.ace.config.AlipayConfig;
import com.ace.entity.Alipay;
import com.ace.entity.Order;
import com.ace.entity.Receipt;
import com.ace.entity.RefundApplication;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Map;

/**
 * @author john
 * @date 19-5-18 下午7:39
 */
public enum AlipayBuilder {
    instance;
    private static final Logger logger = LoggerFactory.getLogger(AlipayBuilder.class);
    private static final String gateway = "https://openapi.alipay.com/gateway.do";
    private final String signType = "RSA2";
    private final String notifyUrl = "http://mrp.baobanwang.com/callback/alipay";


    public String getPay(AlipayConfig config, Order order) {
        try {
            AlipayClient client = new DefaultAlipayClient(gateway, config.getSeller(), config.getPrivateKey(), "json", "utf-8", config.getPublicKey(), signType);
            AlipayTradeAppPayModel payModel = new AlipayTradeAppPayModel();
            payModel.setOutTradeNo(order.getOrderNo());
            payModel.setSubject("爱包办-会议室预定-" + order.getOrderNo());
            payModel.setBody("爱包办-会议室预定-" + order.getOrderNo());
            payModel.setTotalAmount(order.getPayAmount().toString());


            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            request.setBizModel(payModel);
            request.setNotifyUrl(notifyUrl);
            AlipayTradeAppPayResponse response = client.sdkExecute(request);
            return response.getBody();
        } catch (AlipayApiException exp) {
            logger.info("支付宝下单失败", exp.getErrMsg());
            return "";
        }
    }

    public boolean verify(AlipayConfig config, Map<String, String> params) {
        try {
            return AlipaySignature.rsaCheckV1(params, config.getPublicKey(), "utf-8", signType);
        } catch (AlipayApiException e) {
            logger.info("支付宝回调验证异常:" + e.getErrMsg());
        }
        return false;
    }

    public boolean refund(AlipayConfig config, String orderNo, BigDecimal refundAmt, String requestNO) {
        AlipayClient client = new DefaultAlipayClient(gateway, config.getSeller(), config.getPrivateKey(), "json", "utf-8", config.getPublicKey(), signType);
        AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
        refundRequest.setBizContent("{" +
                "\"out_trade_no\":\"" + orderNo + "\"," +
                "\"refund_amount\":" + refundAmt + "," +
                "\"refund_currency\":\"CNY\"," +
                "\"refund_reason\":\"正常退款\"," +
                "\"out_request_no\":\"" + requestNO + "\"" +
                "}");
        try {
            AlipayTradeRefundResponse response = client.execute(refundRequest);
            return response.isSuccess();
        } catch (AlipayApiException e) {
            logger.info("支付宝退款失败::" + e.getErrMsg());
            return false;
        }

    }
}
