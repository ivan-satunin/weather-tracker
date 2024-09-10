package com.me.weathertracker.weather;

import com.me.weathertracker.auth.AppUser;
import com.me.weathertracker.common.utils.SecurityUtils;
import com.me.weathertracker.weather.client.WeatherApiClient;
import com.me.weathertracker.weather.location.Location;
import com.me.weathertracker.weather.location.TrackedLocationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static com.me.weathertracker.common.utils.Weathers.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @Mock
    private WeatherApiClient weatherApiClient;

    @Mock
    private TrackedLocationService trackedLocationService;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    private MockedStatic<SecurityUtils> mockedSecurityUtils;

    @BeforeEach
    void setUp() {
        mockedSecurityUtils = Mockito.mockStatic(SecurityUtils.class);
    }

    @AfterEach
    void close() {
        mockedSecurityUtils.close();
    }

    @Test
    void findForTrackedLocations_ifLocationsNotEmpty_shouldReturnWeathers() {
        final var user = new AppUser();
        user.setId(1);

        mockedSecurityUtils.when(SecurityUtils::currentUser).thenReturn(user);

        final var london = Location.builder()
                .longitude(BigDecimal.valueOf(-0.1257))
                .latitude(BigDecimal.valueOf(51.5085))
                .user(new AppUser(1, null, null))
                .build();
        final var sanFrancisco = Location.builder()
                .longitude(BigDecimal.valueOf(-122.4194))
                .latitude(BigDecimal.valueOf(37.7749))
                .user(new AppUser(1, null, null))
                .build();
        final var weathersByLocation = Map.of(london, LONDON_WEATHER, sanFrancisco, SAN_FRANCISCO_WEATHER);

        weathersByLocation.forEach((location, weather) -> doReturn(weather).when(weatherApiClient).currentWeather(location));

        final var locations = weathersByLocation.keySet().stream().toList();

        doReturn(locations).when(trackedLocationService).findAll();

        final var actualWeathers = weatherService.findWeathersForTrackedLocations();
        final var expectedWeathers = weathersByLocation.values().stream().toList();

        assertThat(actualWeathers).isEqualTo(expectedWeathers);
    }

    @Test
    void findForTrackedLocations_ifTrackedLocationsEmpty_shouldReturnEmptyWeatherList() {
        mockedSecurityUtils.when(SecurityUtils::currentUser).thenReturn(new AppUser());

        doReturn(Collections.emptyList()).when(trackedLocationService).findAll();

        final var actualWeathers = weatherService.findWeathersForTrackedLocations();

        assertThat(actualWeathers).isEmpty();
    }

}