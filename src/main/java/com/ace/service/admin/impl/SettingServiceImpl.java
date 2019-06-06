package com.ace.service.admin.impl;

import com.ace.dao.SettingMapper;
import com.ace.entity.Alipay;
import com.ace.entity.Staff;
import com.ace.entity.Wxpay;
import com.ace.service.admin.SettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author john
 * @date 19-6-5 下午7:31
 */
@Service("admin-setting-service")
public class SettingServiceImpl implements SettingService {

    @Resource
    SettingMapper settingMapper;

    @Override
    public Alipay alipay(Staff staff) {
        return settingMapper.alipay(staff);
    }

    @Override
    public void updateAlipay(Staff staff, Alipay alipay) {
        settingMapper.updateAlipay(staff, alipay);
    }

    @Override
    public Wxpay wxpay(Staff staff) {
        return settingMapper.wxpay(staff);
    }

    @Override
    public void updateWxpay(Staff staff, Wxpay wxpay) {
        settingMapper.updateWxpay(staff, wxpay);
    }

    @Override
    public String protocol(Staff staff) {
        return settingMapper.protocol(staff);
    }

    @Override
    public void updateProtocol(Staff staff, String protocol) {
        settingMapper.updateProtocol(staff, protocol);
    }
}
