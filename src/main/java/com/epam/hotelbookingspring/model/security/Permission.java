package com.epam.hotelbookingspring.model.security;

public enum Permission {
    REQUEST_CREATE("request:create"),
    DEVELOPERS_WRITE("developers:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}