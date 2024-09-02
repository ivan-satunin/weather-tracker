package com.me.weathertracker.weather.client;

import com.me.weathertracker.common.exception.SmthWentWrongException;
import com.me.weathertracker.common.utils.MappingUtils;
import com.me.weathertracker.weather.LocationApiNofFoundException;
import com.me.weathertracker.weather.SearchLocationService;
import com.me.weathertracker.weather.opemWeatherApi.response.LocationApiResponse;
import com.me.weathertracker.weather.opemWeatherApi.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Component
public class LocationApiClient implements SearchLocationService {

    @Value("${weather.api.key}")
    private String appid;
    private final RestClient openWeatherRestClient;

    @Override
    public LocationDto find(String name) {
        final var locationApiResp = openWeatherRestClient.get()
                .uri(uri -> uri
                        .queryParam("appid", appid)
                        .queryParam("q", name)
                        .queryParam("units", "metric")
                        .build()
                )
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new LocationApiNofFoundException(name);
                })
                .body(LocationApiResponse.class);
        if (locationApiResp == null)
            throw new SmthWentWrongException();
        return MappingUtils.locationApiToDto(locationApiResp);
    }
}
