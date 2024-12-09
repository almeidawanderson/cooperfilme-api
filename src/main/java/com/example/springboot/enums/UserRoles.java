package com.example.springboot.enums;

public enum UserRoles {
    ANALISTA("analista"),
    REVISOR("revisor"),
    APROVADOR("aprovador"),
    USER("user");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
