package com.me.weathertracker.common.utils;

import com.me.weathertracker.weather.openWeatherApi.dto.WeatherDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public interface Weathers {

    WeatherDto LONDON_WEATHER = WeatherDto.builder()
            .location(Locations.LONDON_DTO)
            .feelsLike(BigDecimal.valueOf(19.64))
            .tempMin(BigDecimal.valueOf(16.22))
            .tempMax(BigDecimal.valueOf(22.8))
            .pressure(1010)
            .humidity(86)
            .cloudiness(100)
            .currentDate(timestampToDateTime(1725628055, 3600))
            .sunrise(timestampToDateTime(1725600103, 3600))
            .sunset(timestampToDateTime(1725647773, 3600))
            .windDeg(166)
            .windSpeed(BigDecimal.valueOf(1.79))
            .temp(BigDecimal.valueOf(19.4))
            .cloudiness(100)
            .description("Overcast clouds")
            .build();

    WeatherDto BOSTON_WEATHER = WeatherDto.builder()
            .location(Locations.BOSTON_DTO)
            .feelsLike(BigDecimal.valueOf(16.1))
            .tempMin(BigDecimal.valueOf(14.76))
            .tempMax(BigDecimal.valueOf(17.28))
            .pressure(1014)
            .humidity(88)
            .cloudiness(100)
            .currentDate(timestampToDateTime(1725704209, -14400))
            .sunrise(timestampToDateTime(1725704182, -14400))
            .sunset(timestampToDateTime(1725750492, -14400))
            .windDeg(340)
            .windSpeed(BigDecimal.valueOf(4.12))
            .temp(BigDecimal.valueOf(16.13))
            .description("overcast clouds")
            .build();

    WeatherDto SAN_FRANCISCO_WEATHER = WeatherDto.builder()
            .location(Locations.SAN_FRANCISCO_DTO)
            .feelsLike(BigDecimal.valueOf(22.1))
            .tempMin(BigDecimal.valueOf(19.23))
            .tempMax(BigDecimal.valueOf(30.02))
            .pressure(1013)
            .humidity(67)
            .cloudiness(20)
            .currentDate(timestampToDateTime(1725740629, -25200))
            .sunrise(timestampToDateTime(1725716727, -25200))
            .sunset(timestampToDateTime(1725762594, -25200))
            .windDeg(290)
            .windSpeed(BigDecimal.valueOf(5.66))
            .temp(BigDecimal.valueOf(22.09))
            .description("few clouds")
            .build();

    private static LocalDateTime timestampToDateTime(long timestamp, int timezone) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.ofTotalSeconds(timezone));
    }
}
