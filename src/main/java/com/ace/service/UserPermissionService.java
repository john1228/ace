package com.ace.service;

import org.springframework.stereotype.Service;

import com.ace.dao.UserPermissionMapper;
import com.ace.entity.UserPermission;

import javax.annotation.Resource;

@Service("userPermissionService")
public class UserPermissionService extends BaseService<UserPermission> {
    @Resource
    private UserPermissionMapper deletePermissionByUserId;

    public int deletePermissionByUserId(Integer userId) {
        return deletePermissionByUserId.deletePermissionByUserId(userId);
    }

}
