package com.ace.service.impl;

import com.ace.entity.Staff;
import com.ace.service.TokenService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author john
 * @date 19-4-25 下午7:26
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    @Resource
    private RedisTemplate<String, Staff> redisTemplate;

    @Override
    public String createToken(Staff staff) {
        return null;
    }

    @Override
    public boolean checkToken(String token) {
        return redisTemplate.hasKey(token);
    }

    @Override
    public void deleteToken(String token) {
        redisTemplate.delete(token);
    }
}
