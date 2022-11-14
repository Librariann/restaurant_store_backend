package com.restaurant.store.user.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    ADMIN,
    CLIENT;

    @JsonValue
    public String getUserType() {
        return name().toLowerCase();
    }
}
