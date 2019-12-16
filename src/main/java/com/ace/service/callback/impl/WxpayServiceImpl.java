package com.ace.service.callback.impl;

import com.ace.config.WxpayConfig;
import com.ace.dao.RoomMapper;
import com.ace.dao.WxpayMapper;
import com.ace.entity.Receipt;
import com.ace.entity.RefundApplication;
import com.ace.entity.Room;
import com.ace.entity.Wxpay;
import com.ace.service.callback.WxpayService;
import com.ace.util.wxpay.WxpayBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.SortedMap;

/**
 * @author john
 * @date 19-6-17 上午11:57
 */
@Service("cb-wxpay-service")
public class WxpayServiceImpl implements WxpayService {
    @Resource
    WxpayConfig wxpayConfig;

    @Override
    public boolean check(String orderNo, SortedMap<String, Object> params, String sign) {
        return WxpayBuilder.instance.check(wxpayConfig, params, sign);
    }
}
