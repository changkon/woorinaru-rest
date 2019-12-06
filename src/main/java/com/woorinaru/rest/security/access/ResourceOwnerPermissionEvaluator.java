package com.woorinaru.rest.security.access;

import com.woorinaru.rest.security.userdetails.IdTokenUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ResourceOwnerPermissionEvaluator {

    public boolean hasPermissionExcludingRoles(int resourceId, String... roles) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (roles != null) {
            Collection<String> roleCollection = List.of(roles);
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            boolean roleMatch = authorities.stream().anyMatch(auth -> {
                // sanitize. Must remove ROLE_ prefix
                String grantedRole = ((GrantedAuthority) auth).getAuthority().replaceFirst("ROLE_", "");
                return roleCollection.contains(grantedRole);
            });
            if (roleMatch) {
                return true;
            }
        }

        if (user instanceof IdTokenUserDetails) {
            IdTokenUserDetails idTokenUserDetails = (IdTokenUserDetails) user;
            int userId = idTokenUserDetails.getId();
            return userId == resourceId;
        }

        return false;
    }

}
