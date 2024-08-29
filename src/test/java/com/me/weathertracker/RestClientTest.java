package com.me.weathertracker;

import com.me.weathertracker.weather.opemWeatherApi.response.LocationApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

@SpringBootTest
class RestClientTest {
//    @Autowired
//    private FindLocationsService findLocationsService;

    @Test
    void test() {
        final var restClient = RestClient.builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/weather")
                .build();//

        final var q = "London";
        final var appId = "a1dabe8ddd734cd61cfdd9c3b2396966";
        final var responseLocation = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", q)
                        .queryParam("appid", appId)
                        .build())
                .retrieve()
                .body(LocationApiResponse.class);
        System.out.println();
    }
}
