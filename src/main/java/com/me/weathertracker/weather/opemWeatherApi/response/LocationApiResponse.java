package com.me.weathertracker.weather.opemWeatherApi.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LocationApiResponse {
    private Coord coord;
    private Sys sys;
    private String name;

    @Getter
    @Setter
    public static class Coord {
        private BigDecimal lon;
        private BigDecimal lat;
    }

    @Getter
    @Setter
    public static class Sys {
        private String country;
    }
}
