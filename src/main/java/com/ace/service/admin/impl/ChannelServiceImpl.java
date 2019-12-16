package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.ReportCriteria;
import com.ace.dao.handler.RoomReportMapper;
import com.ace.entity.Account;
import com.ace.entity.ChannelReport;
import com.ace.entity.Room;
import com.ace.entity.Staff;
import com.ace.service.admin.ChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author john
 * @date 19-9-25 下午5:30
 */
@Service("admin-channel-service")
public class ChannelServiceImpl implements ChannelService {

    @Resource
    private RoomReportMapper rrMapper;

    @Override
    public void data(Staff staff, DataTable<ChannelReport> dataTable, ReportCriteria criteria) {
        dataTable.setRecordsFiltered(rrMapper.recordsTotal(staff, criteria));
        dataTable.setData(rrMapper.channelReport(staff, criteria));
    }
}
