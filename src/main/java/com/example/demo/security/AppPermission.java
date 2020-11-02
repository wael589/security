package com.example.demo.security;

public enum AppPermission {
    CODING("dev:coding"),
    HACK("dev:hack"),
    PAY("test:pay"),
    GREETING("test:greeting");

    private final String permission;

    AppPermission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
