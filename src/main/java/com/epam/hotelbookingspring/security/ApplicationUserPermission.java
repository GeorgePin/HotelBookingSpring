package com.epam.hotelbookingspring.security;

public enum ApplicationUserPermission {
    ROOM_READ("room:read"),
    ROOM_CREATE("room:create");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
