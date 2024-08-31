package com.me.weathertracker.weather.opemWeatherApi.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class WeatherDto {
    String locationName;
    int timezone;
    BigDecimal temp;
    BigDecimal feelsLike;
    BigDecimal tempMin;
    BigDecimal tempMax;
    int pressure;
    int humidity;
    int cloudiness;
    LocalDateTime sunrise;
    LocalDateTime sunset;
    int windDeg;
    BigDecimal windSpeed;
    String description;
}
