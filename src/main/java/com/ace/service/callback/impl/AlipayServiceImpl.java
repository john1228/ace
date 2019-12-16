package com.ace.service.callback.impl;

import com.ace.config.AlipayConfig;
import com.ace.service.callback.AlipayService;
import com.ace.util.AlipayBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author john
 * @date 19-6-17 上午11:56
 */
@Service("cb-alipay-service")
public class AlipayServiceImpl implements AlipayService {

    @Resource
    private AlipayConfig alipayConfig;

    @Override
    public boolean check(String orderNo, Map<String, String> params) {
        return AlipayBuilder.instance.verify(alipayConfig, params);
    }
}
