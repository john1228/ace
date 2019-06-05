package com.ace.service.admin;

import com.ace.entity.Alipay;
import com.ace.entity.Staff;
import com.ace.entity.Wxpay;

/**
 * @author john
 * @date 19-6-5 下午7:28
 */
public interface SettingService {
    Alipay alipay(Staff staff);

    void updateAlipay(Staff staff, Alipay alipay);

    Wxpay wxpay(Staff staff);

    void updateWxpay(Staff staff, Wxpay wxpay);

    String protocol(Staff staff);

    void updateProtocol(Staff staff, String protocol);
}
