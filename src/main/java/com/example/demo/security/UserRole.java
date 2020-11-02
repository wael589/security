package com.example.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.AppPermission.*;
public enum UserRole {
    DEV(Sets.newHashSet()),
    CEO(Sets.newHashSet(CODING, HACK)),
    ADMINTRAINEE(Sets.newHashSet(CODING));
    private final Set<AppPermission> permissions;

    UserRole(Set<AppPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppPermission> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission->new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
