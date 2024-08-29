package com.me.weathertracker.auth;

import com.me.weathertracker.common.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppUserService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    @Override
    public void register(String login, String password) {
        final var user = AppUser.builder().login(login).password(password).build();
        final var validationResult = userValidator.validate(user);
        if (validationResult.hasErrors())
            throw new ValidationException(validationResult.getErrors(), "auth/sign-up");
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}