package com.me.weathertracker.auth;

import java.util.Optional;

public interface UserRepository {

    Optional<AppUser> findByLogin(String login);

    AppUser save(AppUser user);
}
