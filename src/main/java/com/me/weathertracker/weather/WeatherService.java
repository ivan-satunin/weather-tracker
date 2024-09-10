package com.me.weathertracker.weather;

import com.me.weathertracker.weather.openWeatherApi.dto.WeatherDto;

import java.util.List;

public interface WeatherService {

    List<WeatherDto> findWeathersForTrackedLocations();
}
