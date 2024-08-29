package com.me.weathertracker.weather.opemWeatherApi.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class WeatherDto {
    String name;
    int timezone;
    double temp;
    double feelsLike;
    double tempMin;
    double tempMax;
    double pressure;
    double humidity;
    int cloudiness;
    LocalDateTime sunrise;
    LocalDateTime sunset;
    int windDeg;
    double windSpeed;
    String description;
}
