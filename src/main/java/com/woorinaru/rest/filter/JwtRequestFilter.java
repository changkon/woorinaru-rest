package com.woorinaru.rest.filter;

import com.woorinaru.core.model.security.Role;
import com.woorinaru.core.model.user.User;
import com.woorinaru.core.service.UserServiceImpl;
import com.woorinaru.rest.mapper.user.UserDetailsMapper;
import com.woorinaru.rest.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * This filter extracts the Id Token and sets the user in context (programmatically).
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // Extract the Authorization Header
        String requestTokenHeader = httpServletRequest.getHeader("Authorization");

        if (Objects.nonNull(requestTokenHeader)) {
            // Extract the jwt token
            String jwtToken = requestTokenHeader.replaceAll("^Bearer ", "");
            // Check if the jwtToken is valid.
            boolean isValid = jwtTokenUtil.validateToken(jwtToken);

            if (isValid) {
                // Set the security context
                Role role = jwtTokenUtil.getRoleFromToken(jwtToken);

                if (role.equals(Role.VISITOR)) {
                    UserDetails userDetails = userDetailsMapper.mapAnonymousUser();
                    // TODO determine if key is important
                    AbstractAuthenticationToken authenticationToken = new AnonymousAuthenticationToken("foo", userDetails, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    int userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
                    User user = this.userService.get(userId);
                    this.userService.setUserContext(user);
                    UserDetails userDetails = userDetailsMapper.map(user);
                    AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        // Continue filter
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
