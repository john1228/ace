package com.ace.util.wxpay;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author john
 * @date 19-5-18 下午8:14
 */
@Setter
@Getter
@RequiredArgsConstructor
public class WxPayClient {
    private Logger logger = LoggerFactory.getLogger(WxPayClient.class);
    @NonNull
    private String gateway;
    @NonNull
    private String appId;
    @NonNull
    private String mchId;
    @NonNull
    private String secretKey;
    @NonNull
    private String tradeIp;
    @NonNull
    private String tradeType;
    @NonNull
    private String notifyUrl;
    private String timeExpire;
    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    public WxpayResponse makeRequest(WxpayRequest request) {
        SortedMap<String, Object> params = new TreeMap<>();
        params.put("appid", appId);
        params.put("body", request.getBody());
        params.put("mch_id", mchId);
        params.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
        params.put("notify_url", notifyUrl);
        params.put("out_trade_no", request.getOutTradeNo());
        params.put("spbill_create_ip", tradeIp);
        params.put("total_fee", request.getTotalFee().multiply(new BigDecimal(100)).intValue());
        params.put("trade_type", tradeType);
        if (!Strings.isBlank(timeExpire))
            params.put("time_expire", timeExpire);
        try {
            String signed = md5(params);
            StringBuilder reqXml = new StringBuilder("<xml>");
            params.forEach((k, v) -> reqXml.append("<" + k + "><![CDATA[").append(v).append("]]></" + k + ">"));
            reqXml.append("<sign><![CDATA[").append(signed).append("]]></sign>").append("</xml>");
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(gateway);
            logger.info(reqXml.toString());
            post.setEntity(new StringEntity(reqXml.toString(), Consts.UTF_8));
            CloseableHttpResponse response = client.execute(post);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("统一下单结果:" + result);
            SortedMap<String, Object> resultMap = XMLUtils.xmlToMap(result);
            if ("SUCCESS".equals(resultMap.get("result_code"))) {
                WxpayResponse wxpay = new WxpayResponse(
                        appId,
                        mchId,
                        resultMap.get("prepay_id").toString(),
                        String.valueOf(new Date().getTime()),
                        String.valueOf(new Date().getTime()),
                        System.currentTimeMillis() / 1000,
                        ""
                );
                wxpay.setSign(md5(wxpay.signMap()).toUpperCase());
                return wxpay;
            }
        } catch (Exception e) {
            logger.info("微信统一下单失败:" + e.getMessage());
        }

        return null;
    }

    public boolean refund(WxpayRefundRequest request) {
        SortedMap<String, Object> params = new TreeMap<>();
        params.put("appid", appId);
        params.put("mch_id", mchId);
        params.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
        params.put("out_trade_no", request.getOrderNo());
        params.put("out_refund_no", request.getOrderNo() + "-" + System.currentTimeMillis());
        params.put("total_fee", request.getTotalFee().multiply(new BigDecimal(100)).intValue());
        params.put("refund_fee", request.getRefundFee().multiply(new BigDecimal(100)).intValue());

        try {
            String signed = md5(params);
            StringBuilder reqXml = new StringBuilder("<xml>");
            params.forEach((k, v) -> reqXml.append("<" + k + "><![CDATA[").append(v).append("]]></" + k + ">"));
            reqXml.append("<sign><![CDATA[").append(signed).append("]]></sign>").append("</xml>");

            ClassPathResource resource = new ClassPathResource("cert/wxpay/apiclient_cert.p12");
            InputStream inputStream = resource.getInputStream();
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(inputStream, mchId.toCharArray());

            SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
            SSLConnectionSocketFactory sslf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslf).build();
            HttpPost post = new HttpPost(gateway);
            post.setEntity(new StringEntity(reqXml.toString(), Consts.UTF_8));
            CloseableHttpResponse response = client.execute(post);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("申请退款结果:" + result);
            SortedMap<String, Object> resultMap = XMLUtils.xmlToMap(result);
            if ("SUCCESS".equals(resultMap.get("result_code"))) return true;
        } catch (Exception e) {
            logger.info("微信统一下单失败:" + e.getMessage());
        }

        return false;
    }

    public boolean signatureCheck(SortedMap<String, Object> params, String sign) throws NoSuchAlgorithmException {
        return sign.equals(md5(params).toUpperCase());
    }

    private String md5(SortedMap<String, Object> params) throws NoSuchAlgorithmException {
        StringBuffer sb = new StringBuffer();
        Set es = params.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + secretKey);
        logger.info("当前字符串:" + sb.toString());

        //MD5加密
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(sb.toString().getBytes(Consts.UTF_8));
        byte[] rst = md.digest();
        int j = rst.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = rst[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
}
