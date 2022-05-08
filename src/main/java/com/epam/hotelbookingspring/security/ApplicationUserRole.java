package com.epam.hotelbookingspring.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.epam.hotelbookingspring.security.ApplicationUserPermission.ROOM_CREATE;
import static com.epam.hotelbookingspring.security.ApplicationUserPermission.ROOM_READ;

public enum ApplicationUserRole {
    CLIENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ROOM_CREATE, ROOM_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
