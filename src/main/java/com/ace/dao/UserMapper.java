package com.ace.dao;


import org.apache.ibatis.annotations.Param;

import com.ace.entity.User;


public interface UserMapper {
    User getUserToLoginName(@Param("loginName") String loginName);

}
