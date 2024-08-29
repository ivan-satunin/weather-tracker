package com.me.weathertracker.common.utils;

import com.me.weathertracker.auth.AppUser;
import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtils {

    @Nullable
    public static AppUser currentUser() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated())
            return null;
        return (AppUser) authentication.getPrincipal();
    }
}
