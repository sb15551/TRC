package com.example.trc.model.users;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Surkov Aleksey (stibium128@gmail.com)
 * @date 13.10.2020 10:30
 */
public enum Role {
    Admin(Set.of(Permission.WRITE, Permission.READ)),
    Teacher(Set.of(Permission.WRITE, Permission.READ)),
    Student(Set.of(Permission.READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
