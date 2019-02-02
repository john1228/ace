package com.ace.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ace.controller.admin.bo.UserBO;
import com.ace.dao.UserMapper;
import com.ace.entity.User;
import com.ace.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("userService")
public class UserService extends BaseService<User> {
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    public User getUserToLoginName(String loginName) {
        return userMapper.getUserToLoginName(loginName);
    }

    public PageInfo<UserBO> getUserList(int page) {
        PageHelper.startPage(page, 10);
        List<UserBO> list = userMapper.getUserList();
        PageInfo<UserBO> p = new PageInfo<UserBO>(list);
        return p;
    }

    public UserBO getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public Integer saveUser(User us) {
        us.setCreateTime(StringUtils.getDateToLong(new Date()));
        us.setPortrait("");
        us.setRemark("");
        us.setIsAdmin("N");
        //TODO 密码加密
        Integer count = saveSelect(us);
        return count;
    }

}
