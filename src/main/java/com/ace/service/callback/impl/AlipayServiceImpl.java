package com.ace.service.callback.impl;

import com.ace.dao.AlipayMapper;
import com.ace.dao.RoomMapper;
import com.ace.entity.Alipay;
import com.ace.entity.Room;
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
    RoomMapper roomMapper;
    @Resource
    AlipayMapper alipayMapper;

    @Override
    public boolean check(String orderNo, Map<String, String> params) {
        Room room = roomMapper.appointedRoom(orderNo);
        Alipay alipay = alipayMapper.findBy(room.getProjectId());
        if (alipay == null) {
            return false;
        } else {
            return AlipayBuilder.instance.verify(alipay, params);
        }
    }
}
