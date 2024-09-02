package com.me.weathertracker.weather.opemWeatherApi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Coord {
    private BigDecimal lon;
    private BigDecimal lat;
}
