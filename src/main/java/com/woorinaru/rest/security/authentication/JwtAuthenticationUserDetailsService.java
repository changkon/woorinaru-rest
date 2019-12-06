package com.woorinaru.rest.security.authentication;

import com.woorinaru.core.model.security.Role;
import com.woorinaru.core.model.user.User;
import com.woorinaru.rest.mapper.user.UserDetailsMapper;
import com.woorinaru.rest.mapper.user.UserMapper;
import com.woorinaru.rest.security.token.jwt.JwtTokenUtil;
import com.woorinaru.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) throws UsernameNotFoundException {
        if (!(preAuthenticatedAuthenticationToken.getCredentials() instanceof String)) {
            throw new IllegalArgumentException("Unknown token credentials");
        }

        String authorization = (String) preAuthenticatedAuthenticationToken.getCredentials();
        String jwtToken = authorization.replaceFirst("Bearer ", "");
        boolean isValid = jwtTokenUtil.validateToken(jwtToken);

        if (isValid) {

            // Set the security context
            Role role = jwtTokenUtil.getRoleFromToken(jwtToken);

            if (role.equals(Role.VISITOR)) {
                return userDetailsMapper.mapAnonymousUser();
            } else {
                int userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
                com.woorinaru.rest.dto.user.User userDto = this.userService.get(userId);
                User userModel = UserMapper.MAPPER.mapToModel(userDto);
                return userDetailsMapper.map(userModel);
            }

        } else {
            throw new RuntimeException("Jwt token malformed");
        }
    }

    public JwtTokenUtil getJwtTokenUtil() {
        return jwtTokenUtil;
    }

    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public UserDetailsMapper getUserDetailsMapper() {
        return userDetailsMapper;
    }

    public void setUserDetailsMapper(UserDetailsMapper userDetailsMapper) {
        this.userDetailsMapper = userDetailsMapper;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
