package com.me.weathertracker.common.utils;

import com.me.weathertracker.weather.opemWeatherApi.dto.LocationDto;
import com.me.weathertracker.weather.opemWeatherApi.dto.WeatherDto;
import com.me.weathertracker.weather.opemWeatherApi.response.LocationApiResponse;
import com.me.weathertracker.weather.opemWeatherApi.response.WeatherApiResponse;
import lombok.experimental.UtilityClass;
import org.aspectj.lang.annotation.DeclareWarning;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@UtilityClass
public class MappingUtils {

    public static WeatherDto weatherApiToDto(WeatherApiResponse weatherApi) {
        final var weather = weatherApi.getWeathers().get(0);
        final var main = weatherApi.getMain();
        final var sys = weatherApi.getSys();
        final var wind = weatherApi.getWind();
        return WeatherDto.builder()
                .locationName(weatherApi.getName())
                .feelsLike(toCelsius(main.getFeelsLike()))
                .temp(toCelsius(main.getTemp()))
                .feelsLike(toCelsius(main.getFeelsLike()))
                .tempMin(toCelsius(main.getTempMin()))
                .tempMax(toCelsius(main.getTempMax()))
                .cloudiness(weatherApi.getClouds().getAll())
                .humidity(main.getHumidity())
                .pressure(main.getPressure())
                .description(weather.getDescription())
                .windDeg(wind.getDeg() )
                .windSpeed(BigDecimal.valueOf(wind.getSpeed()).setScale(2, RoundingMode.HALF_UP))
                .sunrise(secondsToLocalDateTime(sys.getSunrise()))
                .sunset(secondsToLocalDateTime(sys.getSunset()))
                .build();
    }

    private static BigDecimal toCelsius(double kelvinTemp) {
        return BigDecimal.valueOf(kelvinTemp).subtract(BigDecimal.valueOf(273)).setScale(2, RoundingMode.HALF_UP);    //TODO magic number
    }

    private static LocalDateTime secondsToLocalDateTime(long seconds) {
        return new Date(seconds).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
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
