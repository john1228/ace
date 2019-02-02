package com.ace.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AdminUserDetailsService userService;

    /**
     * 自定义验证
     */
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        AdminUserDetails user = (AdminUserDetails) userService.loadUserByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("未找到此用户");
        }
        //加密过程在这里体现
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
