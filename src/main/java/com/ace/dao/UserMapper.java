package com.ace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ace.tk.TKMapper;
import com.ace.controller.admin.bo.UserBO;
import com.ace.entity.User;


public interface UserMapper extends TKMapper<User> {
    User getUserToLoginName(@Param("loginName") String loginName);

    List<UserBO> getUserList();

    UserBO getUserById(@Param("id") Integer id);
}
