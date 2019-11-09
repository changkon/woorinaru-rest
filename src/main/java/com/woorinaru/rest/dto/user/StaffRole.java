package com.woorinaru.rest.dto.user;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StaffRole {
    LEADER("leader"),
    VICE_LEADER("vice_leader"),
    SUB_LEADER("sub_leader"),
    TEACHER("teacher");

    private String role;

    private StaffRole(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return this.role;
    }
}
