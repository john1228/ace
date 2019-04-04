package com.ace.service;

import org.springframework.stereotype.Service;

import com.ace.dao.GroupPermissionMapper;
import com.ace.entity.GroupPermission;

import javax.annotation.Resource;

@Service("groupPermissionService")
public class GroupPermissionService {
    @Resource
    private GroupPermissionMapper groupPermissionMapper;

    public int deletePermissionByGroupId(Integer groupId) {
        return groupPermissionMapper.deletePermissionByGroupId(groupId);
    }


}
