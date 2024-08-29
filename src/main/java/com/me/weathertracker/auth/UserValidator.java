package com.me.weathertracker.auth;

import com.me.weathertracker.common.validation.Error;
import com.me.weathertracker.common.validation.ValidationResult;
import com.me.weathertracker.common.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator<AppUser> {
    private final UserDetailsService userDetailsService;

    @Override
    public ValidationResult validate(AppUser user) {
        final var validationResult = new ValidationResult();
        final var login = user.getLogin().trim();
        final var password = user.getPassword().trim();
        if (login.isEmpty() || login.isBlank())
            validationResult.add(Error.of("The Username or email must be non-blank"));
        if (loginExists(login))
            validationResult.add(Error.of("The username or email already taken"));
        if (password.isEmpty() || password.isBlank())
            validationResult.add(Error.of("The password must be non-blank"));
        return validationResult;
    }

    private boolean loginExists(String login) {
        try {
            userDetailsService.loadUserByUsername(login);
            return true;
        } catch (UsernameNotFoundException ex) {
            return false;
        }
    }
}
