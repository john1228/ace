package com.ace.service.admin.impl;

import com.ace.dao.ProtocolMapper;
import com.ace.entity.Staff;
import com.ace.service.admin.ProtocolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author john
 * @date 19-6-5 下午7:31
 */
@Service("admin-protocol-service")
public class ProtocolServiceImpl implements ProtocolService {

    @Resource
    ProtocolMapper protocolMapper;

    @Override
    public String protocol(Staff staff) {
        return protocolMapper.findBy(staff.getOrgId());
    }

    @Override
    public void updateProtocol(Staff staff, String protocol) {
        protocolMapper.update(staff, protocol);
    }
}
