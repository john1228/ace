package com.ace.service;

import org.springframework.stereotype.Service;

import com.ace.dao.UserPermissionMapper;

import javax.annotation.Resource;

@Service("userPermissionService")
public class UserPermissionService {
    @Resource
    private UserPermissionMapper deletePermissionByUserId;

    public int deletePermissionByUserId(Integer userId) {
        return deletePermissionByUserId.deletePermissionByUserId(userId);
    }

}
