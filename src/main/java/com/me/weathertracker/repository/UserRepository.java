package com.me.weathertracker.repository;

import com.me.weathertracker.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByLogin(String login);
}
