package com.me.weathertracker.weather.opemWeatherApi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherApiResponse {
    private Main main;
    private Clouds clouds;
    private Sys sys;
    private String name;
    private Wind wind;
    @JsonProperty("weather")
    private List<Weather> weathers;

    @Getter
    @Setter
    public static class Main {
        private double temp;

        @JsonProperty("feels_like")
        private double feelsLike;

        @JsonProperty("temp_min")
        private double tempMin;

        @JsonProperty("temp_max")
        private double tempMax;

        private int pressure;
        private int humidity;
    }

    @Getter
    @Setter
    public static class Clouds {
        private int all;
    }

    @Getter
    @Setter
    public static class Sys {
        private long sunrise;
        private long sunset;
    }

    @Getter
    @Setter
    public static class Weather {
        private String description;
    }

    @Getter
    @Setter
    public static class Wind {
        private int deg;
        private double speed;
    }
}
