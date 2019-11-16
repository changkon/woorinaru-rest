package com.woorinaru.rest.mapper.user;

import com.woorinaru.core.model.role.Role;
import com.woorinaru.core.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetails map(User user) {
        // Extract role
        Role role = Role.fromUser(user);
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getName())
            .roles(role.toString())
            .accountExpired(false)
            .accountLocked(false)
            .disabled(false)
            .credentialsExpired(false)
            .build();
    }

    public UserDetails mapAnonymousUser() {
        Role role = Role.VISITOR;
        return org.springframework.security.core.userdetails.User
            .builder()
            .roles(role.toString())
            .accountExpired(false)
            .accountLocked(false)
            .disabled(false)
            .credentialsExpired(false)
            .build();
    }
}
