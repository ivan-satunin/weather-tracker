package com.me.weathertracker.weather.client;

import com.me.weathertracker.weather.LocationApiNofFoundException;
import com.me.weathertracker.weather.openWeatherApi.dto.LocationDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static com.me.weathertracker.common.utils.Locations.*;

@RestClientTest(LocationApiClient.class)
public class LocationApiClientTest {

    private static final String LONDON_JSON = """
            {
                "name": "London",
                "coord": {
                    "lon": -0.1257,
                    "lat": 51.5085
                },
                "sys": {
                    "country": "GB"
                }
            }
            """;

    private static final String BOSTON_JSON = """
            {
                "name": "Boston",
                "coord": {
                    "lon": -71.0598,
                    "lat": 42.3584
                },
                "sys": {
                    "country": "US"
                }
            }
            """;

    private static final String SAN_FRANCISCO_JSON = """
            {
                "name": "San Francisco",
                "coord": {
                    "lon": -122.4194,
                    "lat": 37.7749
                },
                "sys": {
                    "country": "US"
                }
            }
            """;

    @Autowired
    LocationApiClient searchLocationService;

    @Autowired
    MockRestServiceServer mockRestServiceServer;

    @Value("${weather.api.key}")
    private String appid;
    @Value("${weather.api.uri}")
    private String openWeatherBaseUri;

    @ParameterizedTest
    @MethodSource("getValidSearchLocationArguments")
    void find_ifLocationNameIsCorrect_shouldReturn(String name, String json, LocationDto expectedLocation) {

        mockRestServiceServer.expect(requestTo(buildLocationUri(name)))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));


        final var actualLocation = searchLocationService.find(name);

        assertThat(actualLocation)
                .isNotNull()
                .isEqualTo(expectedLocation);
    }

    static Stream<Arguments> getValidSearchLocationArguments() {
        return Stream.of(
                Arguments.of("London", LONDON_JSON, LONDON_DTO),
                Arguments.of("Boston", BOSTON_JSON, BOSTON_DTO),
                Arguments.of("San Francisco", SAN_FRANCISCO_JSON, SAN_FRANCISCO_DTO)
        );
    }

    @ParameterizedTest
    @MethodSource("getInvalidSearchLocationArguments")
    void find_ifLocationNameIsNotCorrect_shouldThrow(String name, String exceptionMessage) {

        mockRestServiceServer.expect(requestTo(buildLocationUri(name)))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        assertThatThrownBy(() -> searchLocationService.find(name))
                .isInstanceOf(LocationApiNofFoundException.class)
                .hasMessage(exceptionMessage);
    }

    static Stream<Arguments> getInvalidSearchLocationArguments() {
        final var badLoc = "  qqq q";
        final var empty = " ";
        final var blank = "    ";
        return Stream.of(
                Arguments.of(badLoc, "Issues with geocoding api for location '%s'".formatted(badLoc)),
                Arguments.of(empty, "Issues with geocoding api for location '%s'".formatted(empty)),
                Arguments.of(blank, "Issues with geocoding api for location '%s'".formatted(blank))
        );
    }

    String buildLocationUri(String locationName) {
        return UriComponentsBuilder.fromUriString(openWeatherBaseUri)
                .queryParam("appid", appid)
                .queryParam("q", locationName)
                .queryParam("units", "metric").toUriString();
    }
}