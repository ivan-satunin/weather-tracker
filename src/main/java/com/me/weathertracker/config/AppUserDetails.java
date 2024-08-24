package com.me.weathertracker.config;

import com.me.weathertracker.entity.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class AppUserDetails implements UserDetails {
    private static final List<String> ROLES = List.of("USER");

    private final AppUser user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ROLES.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }
}
