package com.me.weathertracker.common.utils;

import com.me.weathertracker.weather.opemWeatherApi.dto.LocationDto;
import com.me.weathertracker.weather.opemWeatherApi.dto.WeatherDto;
import com.me.weathertracker.weather.opemWeatherApi.response.LocationApiResponse;
import com.me.weathertracker.weather.opemWeatherApi.response.WeatherApiResponse;
import lombok.experimental.UtilityClass;
import org.aspectj.lang.annotation.DeclareWarning;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@UtilityClass
public class MappingUtils {

    public static WeatherDto weatherApiToDto(WeatherApiResponse weatherApi) {
        final var weather = weatherApi.getWeathers().get(0);
        final var main = weatherApi.getMain();
        final var sys = weatherApi.getSys();
        final var wind = weatherApi.getWind();
        final var timezone = weatherApi.getTimezone();
        return WeatherDto.builder()
                .locationName(weatherApi.getName())
                .currentDate(weatherApi.getDt().atOffset(timezone).toLocalDateTime())
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
                .sunrise(sys.getSunrise().atOffset(timezone).toLocalDateTime())
                .sunset(sys.getSunset().atOffset(timezone).toLocalDateTime())
                .build();
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
