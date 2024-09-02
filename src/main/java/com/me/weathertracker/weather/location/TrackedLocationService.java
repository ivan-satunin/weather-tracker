package com.me.weathertracker.weather.location;


import java.math.BigDecimal;
import java.util.List;

public interface TrackedLocationService {

    List<Location> findAll();

    Location track(String locationName, BigDecimal latitude, BigDecimal longitude);

    void stopTrack(BigDecimal latitude, BigDecimal longitude);
}
