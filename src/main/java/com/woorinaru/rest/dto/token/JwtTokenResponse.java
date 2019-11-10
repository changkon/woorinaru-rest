package com.woorinaru.rest.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtTokenResponse {

    @JsonProperty("token")
    private String jwtToken;

    public JwtTokenResponse() {}

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
