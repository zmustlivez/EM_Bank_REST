package com.example.bankcards.enums;

import lombok.Getter;

@Getter
public enum Roles {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    private final String description;


    Roles(String description) {
        this.description = description;
    }

}
