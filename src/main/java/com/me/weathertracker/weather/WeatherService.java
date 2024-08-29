package com.me.weathertracker.weather;

import com.me.weathertracker.weather.location.Location;
import com.me.weathertracker.weather.opemWeatherApi.dto.WeatherDto;

import java.math.BigDecimal;
import java.util.List;

public interface WeatherService {

    WeatherDto find(BigDecimal lon, BigDecimal lat);

    default WeatherDto find(Location location) {
        return find(location.getLongitude(), location.getLatitude());
    }

    List<WeatherDto> findWeathersForTrackedLocations();
}
