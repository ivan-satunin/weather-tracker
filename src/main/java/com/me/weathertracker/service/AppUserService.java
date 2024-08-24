package com.me.weathertracker.service;

import com.me.weathertracker.dto.ReadUserDto;
import com.me.weathertracker.entity.AppUser;
import com.me.weathertracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ReadUserDto register(String login, String password) {
        var user = AppUser.builder().login(login).password(passwordEncoder.encode(password)).build();
        var savedUser = userRepository.save(user);

        return new ReadUserDto(savedUser.getLogin());
    }
}