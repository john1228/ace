package com.ace.dao;

import com.ace.entity.Alipay;
import com.ace.entity.Staff;
import com.ace.entity.Wxpay;
import org.apache.ibatis.annotations.Param;

/**
 * @author john
 * @date 19-6-6 上午9:32
 */
public interface SettingMapper {
    Alipay alipay(@Param("projectId") String projectId);

    void updateAlipay(@Param("staff") Staff staff, @Param("alipay") Alipay alipay);

    Wxpay wxpay(@Param("projectId") String projectId);

    void updateWxpay(@Param("staff") Staff staff, @Param("wxpay") Wxpay wxpay);

    String protocol(@Param("projectId") String projectId);

    void updateProtocol(@Param("staff") Staff staff, @Param("protocol") String protocol);
}
