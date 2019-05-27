package com.ace.controller.api.concerns;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.apache.el.stream.Stream;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import static com.alipay.api.AlipayConstants.CHARSET;

/**
 * @author john
 * @date 19-5-18 下午1:39
 */
public  class Payment {
    private final String GETWAY = "mobile.securitypay.pay";
    @Resource
    RedisTemplate<String, String> redisTemplate;



    public enum ALIPAY {

    }
}
