package com.me.weathertracker.weather.client;

import com.me.weathertracker.common.exception.SmthWentWrongException;
import com.me.weathertracker.weather.location.TrackedLocationService;
import com.me.weathertracker.common.utils.MappingUtils;
import com.me.weathertracker.common.utils.SecurityUtils;
import com.me.weathertracker.weather.WeatherService;
import com.me.weathertracker.weather.opemWeatherApi.response.WeatherApiResponse;
import com.me.weathertracker.weather.opemWeatherApi.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class WeatherApiClient implements WeatherService {

    @Value("${weather.api.key}")
    private String appid;
    private final RestClient openWeatherRestClient;
    private final TrackedLocationService trackedLocationService;


    @Override
    public WeatherDto find(BigDecimal lon, BigDecimal lat) {
        final var weatherApiResp = openWeatherRestClient
                .get()
                .uri(uri -> uri
                        .queryParam("appid", appid)
                        .queryParam("lon", lon)
                        .queryParam("lat", lat)
                        .queryParam("units", "metric")
                        .build()
                )
                .retrieve()
                .body(WeatherApiResponse.class);
        if (weatherApiResp == null)
            throw new SmthWentWrongException();
        return MappingUtils.weatherApiToDto(weatherApiResp);
    }

    @Override
    public List<WeatherDto> findWeathersForTrackedLocations() {
        final var currentUser = SecurityUtils.currentUser();
        if (currentUser == null) {
            return Collections.emptyList();
        }
        return trackedLocationService.findAll().stream()
                .filter(location -> location.getUser().getId().equals(currentUser.getId()))
                .map(this::find)
                .toList();

    }
}
