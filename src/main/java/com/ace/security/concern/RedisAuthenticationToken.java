package com.ace.security.concern;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collection;

/**
 * @author john
 * @date 19-5-16 下午6:43
 */
public class RedisAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = -1322096112511224564L;
    private final Object principal;
    private Object credentials;

    public RedisAuthenticationToken(Object principal, Object credentials) {
        super((Collection) null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
