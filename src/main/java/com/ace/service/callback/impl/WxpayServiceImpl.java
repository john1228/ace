package com.ace.service.callback.impl;

import com.ace.dao.RoomMapper;
import com.ace.dao.SettingMapper;
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
    RoomMapper roomMapper;
    @Resource
    SettingMapper settingMapper;


    @Override
    public boolean check(String orderNo, SortedMap<String, Object> params, String sign) {
        Room room = roomMapper.appointedRoom(orderNo);
        Wxpay wxpay = settingMapper.wxpay(room.getProjectId());
        if (wxpay == null) {
            return false;
        } else {
            return WxpayBuilder.instance.check(wxpay, params, sign);
        }
    }
}
