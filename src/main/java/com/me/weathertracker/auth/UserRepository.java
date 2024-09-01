package com.me.weathertracker.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository {

    Optional<AppUser> findByLogin(String login);

    AppUser save(AppUser user);
}
