package com.me.weathertracker.weather.openWeatherApi.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationApiResponse {
    private Coord coord;
    private Sys sys;
    private String name;

    @Setter
    @Getter
    public static class Sys {
        private String country;
    }
}
