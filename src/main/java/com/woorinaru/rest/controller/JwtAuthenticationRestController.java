package com.woorinaru.rest.controller;

import com.woorinaru.rest.dto.token.JwtTokenResponse;
import com.woorinaru.rest.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/authenticate")
public class JwtAuthenticationRestController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<JwtTokenResponse> createAuthenticationToken(@RequestHeader(value="Authorization", required=false) String authorizationHeader) {
        if (Objects.isNull(authorizationHeader)) {
            String token = this.authenticationService.generateAccessToken(Optional.empty());
            JwtTokenResponse response = new JwtTokenResponse();
            response.setJwtToken(token);
            return ResponseEntity.ok().body(response);
        }
        String idToken = authorizationHeader.replaceFirst("^Bearer ", "");
        String token = this.authenticationService.generateAccessToken(Optional.of(idToken));
        JwtTokenResponse response = new JwtTokenResponse();
        response.setJwtToken(token);
        return ResponseEntity.ok().body(response);
    }
}
