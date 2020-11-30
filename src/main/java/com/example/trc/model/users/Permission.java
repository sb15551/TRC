package com.example.trc.model.users;

/**
 * @author Surkov Aleksey (stibium128@gmail.com)
 * @date 13.10.2020 16:00
 */
public enum Permission {
    READ("read"),
    WRITE("write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
