package com.woorinaru.rest.security.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IdTokenUserDetails implements UserDetails {

    private int id;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    private IdTokenUserDetails(int id, String username, Collection<? extends GrantedAuthority> authorities, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class IdTokenUserDetailsBuilder {
        private int id;
        private String username;
        private Collection<? extends GrantedAuthority> authorities;
        private boolean isAccountNonExpired;
        private boolean isAccountNonLocked;
        private boolean isCredentialsNonExpired;
        private boolean isEnabled;

        private IdTokenUserDetailsBuilder() {}

        public static IdTokenUserDetailsBuilder builder() {
            return new IdTokenUserDetailsBuilder();
        }

        public IdTokenUserDetailsBuilder id(int id) {
            this.id = id;
            return this;
        }

        public IdTokenUserDetailsBuilder username(String username) {
            this.username = username;
            return this;
        }

        public IdTokenUserDetailsBuilder withRoles(String... roles) {
            List<GrantedAuthority> authorities = Stream.of(roles)
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
            this.authorities = authorities;
            return this;
        }

        public IdTokenUserDetailsBuilder accountExpired(boolean expired) {
            this.isAccountNonExpired = !expired;
            return this;
        }

        public IdTokenUserDetailsBuilder accountLocked(boolean locked) {
            this.isAccountNonLocked = !locked;
            return this;
        }

        public IdTokenUserDetailsBuilder credentialsExpired(boolean expired) {
            this.isCredentialsNonExpired = !expired;
            return this;
        }

        public IdTokenUserDetailsBuilder enabled(boolean enabled) {
            this.isEnabled = enabled;
            return this;
        }

        public IdTokenUserDetails build() {
            return new IdTokenUserDetails(this.id, this.username, this.authorities, this.isAccountNonExpired, this.isAccountNonLocked, this.isCredentialsNonExpired, this.isEnabled);
        }

    }
}
