package com.me.weathertracker.weather.openWeatherApi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.me.weathertracker.common.deserialize.ZoneOffsetJsonDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.util.List;

@Getter
@Setter
public class WeatherApiResponse {
    private Main main;
    private Clouds clouds;
    private Sys sys;
    private long dt;
    @JsonDeserialize(using = ZoneOffsetJsonDeserializer.class)
    private ZoneOffset timezone;
    private Coord coord;
    private String name;
    private Wind wind;
    @JsonProperty("weather")
    private List<Weather> weathers;

    @Getter
    @Setter
    public static class Main {
        private BigDecimal temp;

        @JsonProperty("feels_like")
        private BigDecimal feelsLike;

        @JsonProperty("temp_min")
        private BigDecimal tempMin;

        @JsonProperty("temp_max")
        private BigDecimal tempMax;

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
        private String country;
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
        private BigDecimal speed;
    }
}
