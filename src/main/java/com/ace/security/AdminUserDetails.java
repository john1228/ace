package com.ace.security;

import java.util.Collection;

import com.ace.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.ace.entity.User;

public class AdminUserDetails extends User implements UserDetails {

    private String role;
    private Account account;

    public AdminUserDetails(User user, String role, Account account) {
        super(user);
        this.role = role;
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        StringBuilder commaBuilder = new StringBuilder();
        commaBuilder.append(role);
        String authorities = commaBuilder.toString();
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public String getUsername() {
        return super.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
