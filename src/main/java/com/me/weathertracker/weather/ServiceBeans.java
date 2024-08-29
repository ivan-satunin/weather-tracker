package com.me.weathertracker.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Configuration
public class ServiceBeans {

    @Bean
    public RestClient openWeatherRestClient(@Value("${weather.api.uri}") String openWeatherBaseUri) {
        return RestClient.create(openWeatherBaseUri);
    }

}
