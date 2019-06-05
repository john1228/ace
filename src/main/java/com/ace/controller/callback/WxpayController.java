package com.ace.controller.callback;

import com.ace.entity.Receipt;
import com.ace.service.api.OrderService;
import com.ace.util.wxpay.WxpayBuilder;
import com.ace.util.wxpay.XMLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.SortedMap;

/**
 * @author john
 * @date 19-6-5 上午9:38
 */
public class WxpayController {
    Logger logger = LoggerFactory.getLogger(WxpayController.class);
    @Resource
    OrderService orderService;

    @RequestMapping("/callback/wx")
    public String home(HttpServletRequest request) {
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr = new String(outSteam.toByteArray(), "utf-8");
            logger.info("微信回调::" + resultStr);
            SortedMap<String, Object> resultMap = XMLUtils.xmlToMap(resultStr);//将xml转成排序之后的map
            String result_code = resultMap.get("result_code").toString();//业务结果
            String sign = resultMap.get("sign").toString();//签名
            //签名验证
            if (WxpayBuilder.instance.signatureCheck(null, resultMap, sign)) {
                if (result_code.equals("SUCCESS")) {
                    String orderNo = resultMap.get("out_trade_no").toString();//订单号
                    String tradeNo = resultMap.get("transaction_id").toString();//微信支付订单号  类似于支付宝的交易号
                    String seller = resultMap.get("mch_id").toString();//用户在商户appid下的唯一标识
                    String buyer = resultMap.get("openid").toString();//用户在商户appid下的唯一标识
                    BigDecimal totalFee = new BigDecimal(resultMap.get("total_fee").toString());//订单总金额  单位为 分
                    BigDecimal price = totalFee.divide(new BigDecimal(100));
                    Receipt receipt = new Receipt();
                    receipt.setNo(tradeNo);
                    receipt.setOrderNo(orderNo);
                    receipt.setSeller(seller);
                    receipt.setBuyer(buyer);
                    receipt.setPrice(price);
                    orderService.paying(receipt, "微信");
                    return "<xml>\n" +
                            "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                            "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                            "</xml>";
                }
            }
        } catch (Exception ex) {
            logger.warn("微信支付回调地址处理异常:{}", ex);
            ex.printStackTrace();
        }
        return null;
    }
}
