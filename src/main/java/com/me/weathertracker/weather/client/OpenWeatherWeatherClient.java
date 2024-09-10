package com.me.weathertracker.weather.client;

import com.me.weathertracker.common.exception.SmthWentWrongException;
import com.me.weathertracker.weather.WeatherService;
import com.me.weathertracker.weather.location.TrackedLocationService;
import com.me.weathertracker.common.utils.MappingUtils;
import com.me.weathertracker.common.utils.SecurityUtils;
import com.me.weathertracker.weather.openWeatherApi.response.Coord;
import com.me.weathertracker.weather.openWeatherApi.response.WeatherApiResponse;
import com.me.weathertracker.weather.openWeatherApi.dto.WeatherDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Component
public class OpenWeatherWeatherClient extends OpenWeatherApiClient implements WeatherApiClient {

    public OpenWeatherWeatherClient(RestClient.Builder builder, @Value("${weather.api.uri}") String openWeatherBaseUri) {
        super(builder, openWeatherBaseUri);
    }


    @Override
    public WeatherDto currentWeather(BigDecimal lon, BigDecimal lat) {
        final var weatherApiResp = openWeatherRestClient
                .get()
                .uri(uri -> uri
                        .queryParam("appid", getAppid())
                        .queryParam("lon", lon)
                        .queryParam("lat", lat)
                        .queryParam("units", "metric")
                        .build()
                )
                .retrieve()
                .body(WeatherApiResponse.class);
        if (weatherApiResp == null)
            throw new SmthWentWrongException();
        weatherApiResp.setCoord(new Coord(lon, lat));// Set the old coordinates because the current api can return changed coordinate which creates an inconsistency with db.
        return MappingUtils.weatherApiToDto(weatherApiResp);
    }
}
