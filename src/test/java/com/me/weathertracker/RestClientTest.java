package com.me.weathertracker;

import com.me.weathertracker.weather.openWeatherApi.response.LocationApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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

    @Test
    void dateTest() {
        long timestamp = 1725212000; // значение поля "dt"
        int timezoneOffsetSeconds = -25200; // значение поля "timezone"

        // Переводим Unix timestamp в Instant
        Instant instant = Instant.ofEpochSecond(timestamp);

        // Переводим смещение в зону времени (ZoneOffset)
        ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(timezoneOffsetSeconds);

        // Конвертируем Instant в LocalDateTime с учетом часового пояса
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
        System.out.println("LocalDateTime: " + localDateTime);
    }
}
