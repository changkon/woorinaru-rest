package com.woorinaru.rest.service.impl;

import com.woorinaru.core.model.user.User;
import com.woorinaru.rest.security.token.identity.IdTokenUtil;
import com.woorinaru.rest.security.token.jwt.JwtTokenUtil;
import com.woorinaru.rest.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Profile({"default", "dev", "prod"})
public class AuthenticationServiceImpl implements AuthenticationService {

    private IdTokenUtil idTokenUtil;
    private JwtTokenUtil jwtTokenUtil;

    public AuthenticationServiceImpl() {}

    @Transactional
    @Override
    public String generateAccessToken(Optional<String> idToken) {
        if (idToken.isPresent()) {
            User user = idTokenUtil.retrieveUserAndCreateIfNotExists(idToken.get());
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
}
