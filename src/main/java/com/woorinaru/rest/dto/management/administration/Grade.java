package com.woorinaru.rest.dto.management.administration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Grade {
    BEGINNER("beginner"),
    INTERMEDIATE("intermediate"),
    TUTORING("tutoring"),
    OUTING("outing");

    private String grade;

    private Grade(String grade) {
        this.grade = grade;
    }

    @JsonValue
    public String getGrade() {
        return this.grade;
    }
}
