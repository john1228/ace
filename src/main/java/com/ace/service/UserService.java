package com.ace.service;


import org.springframework.stereotype.Service;

import com.ace.dao.UserMapper;
import com.ace.entity.User;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service("userService")
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getUserToLoginName(String loginName) {
        return userMapper.getUserToLoginName(loginName);
    }
}
