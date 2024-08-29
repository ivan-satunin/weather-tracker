package com.me.weathertracker.weather.opemWeatherApi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class LocationDto {
    private String name;
    private String country;
    private BigDecimal lon;
    private BigDecimal lat;
}
