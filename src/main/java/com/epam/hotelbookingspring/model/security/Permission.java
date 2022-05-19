package com.epam.hotelbookingspring.model.security;

public enum Permission {
    REQUEST_CREATE("request:create"),
    REQUESTS_READ("requests:read"),
    REQUESTS_READ_ALL("requests:readAll");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}