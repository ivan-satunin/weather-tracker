package com.me.weathertracker.weather;

import com.me.weathertracker.common.utils.SecurityUtils;
import com.me.weathertracker.weather.client.WeatherApiClient;
import com.me.weathertracker.weather.location.TrackedLocationService;
import com.me.weathertracker.weather.openWeatherApi.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherApiClient weatherApiClient;
    private final TrackedLocationService trackedLocationService;

    @Override
    public List<WeatherDto> findWeathersForTrackedLocations() {
        final var currentUser = SecurityUtils.currentUser();
        if (currentUser == null) {
            return Collections.emptyList();
        }
        return trackedLocationService.findAll().stream()
                .filter(location -> location.getUser().getId().equals(currentUser.getId()))
                .map(weatherApiClient::currentWeather)
                .toList();
    }
}
