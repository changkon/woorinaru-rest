package com.woorinaru.rest.dto.management.administration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Team {

    PLANNING("planning"),
    DESIGN("design"),
    MEDIA("media"),
    EDUCATION("education");

    private String team;

    private Team(String team) {
        this.team = team;
    }

    @JsonValue
    public String getTeam() {
        return this.team;
    }

}
