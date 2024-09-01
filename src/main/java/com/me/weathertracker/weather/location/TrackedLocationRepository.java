package com.me.weathertracker.weather.location;

import com.me.weathertracker.auth.AppUser;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TrackedLocationRepository {

    Optional<Location> findByNameCoordAndUser(String name, BigDecimal latitude, BigDecimal longitude, AppUser user);

    List<Location> findAll();

    Location save(Location location);
}
