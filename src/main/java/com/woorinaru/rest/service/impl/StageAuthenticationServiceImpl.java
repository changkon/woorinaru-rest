package com.woorinaru.rest.service.impl;

import com.woorinaru.core.model.user.User;
import com.woorinaru.rest.mapper.user.UserMapper;
import com.woorinaru.rest.security.token.identity.IdTokenUtil;
import com.woorinaru.rest.security.token.jwt.JwtTokenUtil;
import com.woorinaru.rest.service.AuthenticationService;
import com.woorinaru.rest.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("authenticationServiceImpl")
@Profile({"stage"})
public class StageAuthenticationServiceImpl implements AuthenticationService {

    private IdTokenUtil idTokenUtil;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    public StageAuthenticationServiceImpl() {}

    @Transactional
    @Override
    public String generateAccessToken(Optional<String> idToken) {
        if (idToken.isPresent()) {
            // Get email field from JWT token
            String jws = idToken.get();
            int i = jws.lastIndexOf('.');
            String withoutSignature = jws.substring(0, i + 1);
            Jwt<Header, Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);
            Claims claims = untrusted.getBody();
            String email = claims.get("email", String.class);
            com.woorinaru.rest.dto.user.User userDto = this.userService.getByEmail(email);
            User user = UserMapper.MAPPER.mapToModel(userDto);
            return jwtTokenUtil.generateToken(Optional.of(user));
        } else {
            return jwtTokenUtil.generateToken();
        }
    }

    public IdTokenUtil getIdTokenUtil() {
        return idTokenUtil;
    }

    @Autowired
    public void setIdTokenUtil(IdTokenUtil idTokenUtil) {
        this.idTokenUtil = idTokenUtil;
    }

    public JwtTokenUtil getJwtTokenUtil() {
        return jwtTokenUtil;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
