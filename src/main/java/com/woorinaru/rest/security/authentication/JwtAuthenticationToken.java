package com.woorinaru.rest.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * This is a container for the jwt token. This is used during the authentication process.
 */

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String token;

    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        // no nothing
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public void setDetails(Object details) {
        // do nothing
    }

    @Override
    public void eraseCredentials() {
        this.token = null;
    }

    public String getToken() {
        return this.token;
    }
}
