package com.me.weathertracker.weather.openWeatherApi.dto;

import lombok.*;

import java.math.BigDecimal;

@Value
@Builder
public class LocationDto {
    String name;
    String country;
    BigDecimal lon;
    BigDecimal lat;
}
