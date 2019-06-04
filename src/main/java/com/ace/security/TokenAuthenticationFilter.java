package com.ace.security;

import com.ace.entity.Account;
import com.ace.service.concerns.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author john
 * @date 19-6-3 下午7:00
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String SPRING_SECURITY_ACCOUNT_SESSION_KEY = "CURRENT::ACCOUNT";
    public static final String SPRING_SECURITY_FORM_TOKEN_KEY = "token";


    public TokenAuthenticationFilter(String processUrl) {
        super(new AntPathRequestMatcher(processUrl, "GET"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getParameter(SPRING_SECURITY_FORM_TOKEN_KEY);
        if (token != null) {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(token, null);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
        return null;
    }
}
