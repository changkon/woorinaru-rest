package com.woorinaru.rest.security.authentication;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class GoogleIdTokenVerifier implements IdTokenVerifier {
    @Override
    public boolean canVerify(String token) {
        return false;
    }

    @Override
    public Optional<Map<String, Object>> getClaims(String token) {
        return Optional.empty();
    }
}
