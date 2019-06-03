package com.ace.security;

import java.util.Collection;

import com.ace.service.concerns.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {
    Logger logger = LoggerFactory.getLogger(AdminAuthenticationProvider.class);
    @Autowired
    private AdminUserDetailsService userService;
    @Autowired
    private TokenService tokenService;

    /**
     * 自定义验证
     */
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        logger.info("认证登录");
        String username = authentication.getName();

        return new TokenAuthentication("");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
