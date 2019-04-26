package com.ace.service;

import com.ace.entity.Staff;

/**
 * @author john
 * @date 19-4-25 下午7:17
 */
public interface TokenService {
    String createToken(Staff staff);

    boolean checkToken(String token);

    void deleteToken(String token);
}
