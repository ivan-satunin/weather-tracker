package com.me.weathertracker.weather.location;

import com.me.weathertracker.auth.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TrackedLocationRepository {

//    @Query("select loc from Location loc " +
//            "where loc.name = :name and loc.longitude = :longitude and loc.latitude = :latitude and loc.user = :user")
    Optional<Location> findByNameCoordAndUser(String name, BigDecimal latitude, BigDecimal longitude, AppUser user);

    List<Location> findAll();

    Location save(Location location);
}
