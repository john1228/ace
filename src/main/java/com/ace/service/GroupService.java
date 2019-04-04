package com.ace.service;

import org.springframework.stereotype.Service;

import com.ace.dao.GroupMapper;
import com.ace.entity.Group;

import javax.annotation.Resource;

@Service("groupService")
public class GroupService {
    @Resource
    private GroupMapper groupMapper;

    public Group queryByName(String name) {
        return groupMapper.queryByName(name);
    }

}
