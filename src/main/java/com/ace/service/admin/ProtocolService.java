package com.ace.service.admin;

import com.ace.entity.Staff;

/**
 * @author john
 * @date 19-6-5 下午7:28
 */
public interface ProtocolService {

    String protocol(Staff staff);

    void updateProtocol(Staff staff, String protocol);
}
