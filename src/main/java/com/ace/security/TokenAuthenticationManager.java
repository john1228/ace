package com.ace.security;

import com.ace.entity.Account;
import com.ace.service.concerns.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author john
 * @date 19-6-4 上午10:02
 */
public class TokenAuthenticationManager implements AuthenticationManager {
    private static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();
    private final TokenService tokenService;

    public TokenAuthenticationManager(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        if (token != null) {
            Account account = tokenService.account(token);
            return new UsernamePasswordAuthenticationToken(token, account, AUTHORITIES);
        }
        throw new BadCredentialsException("Bad Credentials");
    }
}
