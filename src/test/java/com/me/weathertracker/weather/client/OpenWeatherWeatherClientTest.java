package com.me.weathertracker.weather.client;

import com.me.weathertracker.weather.openWeatherApi.dto.WeatherDto;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static com.me.weathertracker.common.utils.Weathers.*;

@RestClientTest(OpenWeatherWeatherClient.class)
class OpenWeatherWeatherClientTest {

    private static final String LONDON_WEATHER_JSON = readWeatherJsonFromResources("london_weather");

    private static final String BOSTON_WEATHER_JSON = readWeatherJsonFromResources("boston_weather");

    private static final String SAN_FRANCISCO_WEATHER_JSON = readWeatherJsonFromResources("san_francisco_weather");

    @Autowired
    OpenWeatherWeatherClient weatherApiClient;

    @Autowired
    MockRestServiceServer mockRestServiceServer;

    @Value("${weather.api.key}")
    String appid;

    @Value("${weather.api.uri}")
    String openWeatherBaseUri;


    @ParameterizedTest
    @MethodSource("getWeatherFindArguments")
    void find_ifCoordinatesIsCorrect_shouldCurrentWeather(BigDecimal longitude, BigDecimal latitude, String json, WeatherDto expectedWeather) {

        mockRestServiceServer.expect(requestTo(buildWeatherUri(longitude, latitude)))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        final var actualWeather = weatherApiClient.currentWeather(longitude, latitude);

        assertThat(actualWeather)
                .isNotNull()
                .isEqualTo(expectedWeather);
    }

    static Stream<Arguments> getWeatherFindArguments() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(-0.1257), BigDecimal.valueOf(51.5085), LONDON_WEATHER_JSON, LONDON_WEATHER),
                Arguments.of(BigDecimal.valueOf(-71.0598), BigDecimal.valueOf(42.3584), BOSTON_WEATHER_JSON, BOSTON_WEATHER),
                Arguments.of(BigDecimal.valueOf(-122.4194), BigDecimal.valueOf(37.7749), SAN_FRANCISCO_WEATHER_JSON, SAN_FRANCISCO_WEATHER)
        );
    }

    String buildWeatherUri(BigDecimal lon, BigDecimal lat) {
        return UriComponentsBuilder.fromUriString(openWeatherBaseUri)
                .queryParam("appid", appid)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .queryParam("units", "metric")
                .toUriString();
    }

    @SneakyThrows
    static String readWeatherJsonFromResources(String fileName) {
        return Files.readString(Path.of("src", "test", "resources", "json", "weathers", "%s.json".formatted(fileName)));
    }
}
