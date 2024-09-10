package com.me.weathertracker.common.utils;

import com.me.weathertracker.weather.openWeatherApi.dto.LocationDto;

import java.math.BigDecimal;

public interface Locations {

    LocationDto LONDON_DTO = LocationDto.builder()
            .name("London")
            .country("GB")
            .lon(BigDecimal.valueOf(-0.1257))
            .lat(BigDecimal.valueOf(51.5085))
            .build();

    LocationDto BOSTON_DTO = LocationDto.builder()
            .name("Boston")
            .country("US")
            .lon(BigDecimal.valueOf(-71.0598))
            .lat(BigDecimal.valueOf(42.3584))
            .build();

    LocationDto SAN_FRANCISCO_DTO = LocationDto.builder()
            .name("San Francisco")
            .country("US")
            .lon(BigDecimal.valueOf(-122.4194))
            .lat(BigDecimal.valueOf(37.7749))
            .build();
}
