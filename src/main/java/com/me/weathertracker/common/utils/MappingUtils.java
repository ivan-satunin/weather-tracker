package com.me.weathertracker.common.utils;

import com.me.weathertracker.weather.openWeatherApi.dto.LocationDto;
import com.me.weathertracker.weather.openWeatherApi.dto.WeatherDto;
import com.me.weathertracker.weather.openWeatherApi.response.LocationApiResponse;
import com.me.weathertracker.weather.openWeatherApi.response.WeatherApiResponse;
import lombok.experimental.UtilityClass;

import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class MappingUtils {

    public static WeatherDto weatherApiToDto(WeatherApiResponse weatherApi) {
        final var weather = weatherApi.getWeathers().get(0);
        final var main = weatherApi.getMain();
        final var sys = weatherApi.getSys();
        final var locationDto = LocationDto.builder()
                .name(weatherApi.getName())
                .lat(weatherApi.getCoord().getLat())
                .lon(weatherApi.getCoord().getLon())
                .country(sys.getCountry())
                .build();
        final var wind = weatherApi.getWind();
        final var timezone = weatherApi.getTimezone();
        return WeatherDto.builder()
                .location(locationDto)
                .currentDate(dateTimeFromTimestamp(weatherApi.getDt(), timezone))
                .feelsLike(main.getFeelsLike())
                .temp(main.getTemp())
                .feelsLike(main.getFeelsLike())
                .tempMin(main.getTempMin())
                .tempMax(main.getTempMax())
                .cloudiness(weatherApi.getClouds().getAll())
                .humidity(main.getHumidity())
                .pressure(main.getPressure())
                .description(weather.getDescription())
                .windDeg(wind.getDeg())
                .windSpeed(wind.getSpeed().setScale(2, RoundingMode.HALF_UP))
                .sunrise(dateTimeFromTimestamp(sys.getSunrise(), timezone))
                .sunset(dateTimeFromTimestamp(sys.getSunset(), timezone))
                .build();
    }

    private static LocalDateTime dateTimeFromTimestamp(long timestamp, ZoneOffset timezone) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), timezone);
    }

    public static LocationDto locationApiToDto(LocationApiResponse locationApi) {
        return LocationDto.builder()
                .name(locationApi.getName())
                .country(locationApi.getSys().getCountry())
                .lon(locationApi.getCoord().getLon())
                .lat(locationApi.getCoord().getLat())
                .build();
    }
}
