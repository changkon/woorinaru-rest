package com.woorinaru.rest.service;

import java.util.Optional;

public interface AuthenticationService {
    String generateAccessToken(Optional<String> idToken);
}
