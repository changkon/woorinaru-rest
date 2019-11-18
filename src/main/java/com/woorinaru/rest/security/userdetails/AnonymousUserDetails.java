package com.woorinaru.rest.security.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnonymousUserDetails implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    private AnonymousUserDetails(Collection<? extends GrantedAuthority> authorities, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.authorities = authorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public static class AnonymousUserDetailsBuilder {
        private Collection<? extends GrantedAuthority> authorities;
        private boolean isAccountNonExpired;
        private boolean isAccountNonLocked;
        private boolean isCredentialsNonExpired;
        private boolean isEnabled;

        private AnonymousUserDetailsBuilder() {}

        public static AnonymousUserDetailsBuilder builder() {
            return new AnonymousUserDetailsBuilder();
        }

        public AnonymousUserDetailsBuilder withRoles(String... roles) {
            List<GrantedAuthority> authorities = Stream.of(roles)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
            this.authorities = authorities;
            return this;
        }

        public AnonymousUserDetailsBuilder accountExpired(boolean expired) {
            this.isAccountNonExpired = !expired;
            return this;
        }

        public AnonymousUserDetailsBuilder accountLocked(boolean locked) {
            this.isAccountNonLocked = !locked;
            return this;
        }

        public AnonymousUserDetailsBuilder credentialsExpired(boolean expired) {
            this.isCredentialsNonExpired = !expired;
            return this;
        }

        public AnonymousUserDetailsBuilder enabled(boolean enabled) {
            this.isEnabled = enabled;
            return this;
        }

        public AnonymousUserDetails build() {
            return new AnonymousUserDetails(this.authorities, this.isAccountNonExpired, this.isAccountNonLocked, this.isCredentialsNonExpired, this.isEnabled);
        }

    }
}
