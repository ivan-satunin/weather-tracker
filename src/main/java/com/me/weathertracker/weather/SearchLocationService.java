package com.me.weathertracker.weather;

import com.me.weathertracker.weather.opemWeatherApi.dto.LocationDto;

public interface SearchLocationService {

    LocationDto find(String name);
}
