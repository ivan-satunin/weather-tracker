package com.me.weathertracker.weather.client;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;

public abstract class OpenWeatherApiClient {

    @Getter
    @Value("${weather.api.key}")
    private String appid;

    protected final RestClient openWeatherRestClient;

    public OpenWeatherApiClient(RestClient.Builder builder, @Value("${weather.api.uri}") String openWeatherBaseUri) {
        this.openWeatherRestClient = builder.baseUrl(openWeatherBaseUri).build();
    }

}
