package com.me.weathertracker.weather.client;

import com.me.weathertracker.weather.location.Location;
import com.me.weathertracker.weather.openWeatherApi.dto.WeatherDto;

import java.math.BigDecimal;

public interface WeatherApiClient {

    WeatherDto currentWeather(BigDecimal lon, BigDecimal lat);

    default WeatherDto currentWeather(Location location) {
        return currentWeather(location.getLongitude(), location.getLatitude());
    }
}
