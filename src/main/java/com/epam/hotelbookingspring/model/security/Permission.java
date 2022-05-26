package com.epam.hotelbookingspring.model.security;

public enum Permission {
    REQUEST_CREATE("request:create"),
    REQUESTS_READ("requests:read"),
    REQUESTS_READ_ALL("requests:readAll"),
    ROOMS_READ("rooms:read"),
    ROOM_EDIT("room:edit"),
    ROOM_DELETE("room:delete"),
    REQUEST_HANDLE("request:handle");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}