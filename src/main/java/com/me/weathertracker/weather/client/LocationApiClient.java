package com.me.weathertracker.weather.client;

import com.me.weathertracker.common.exception.SmthWentWrongException;
import com.me.weathertracker.common.utils.MappingUtils;
import com.me.weathertracker.weather.LocationApiNofFoundException;
import com.me.weathertracker.weather.SearchLocationService;
import com.me.weathertracker.weather.openWeatherApi.response.LocationApiResponse;
import com.me.weathertracker.weather.openWeatherApi.dto.LocationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class LocationApiClient extends OpenWeatherApiClient implements SearchLocationService {

    public LocationApiClient(RestClient.Builder builder, @Value("${weather.api.uri}") String openWeatherBaseUri) {
        super(builder, openWeatherBaseUri);
    }

    @Override
    public LocationDto find(String name) {
        if (name.isEmpty() || name.isBlank())
            throw new LocationApiNofFoundException(name);
        final var locationApiResp = openWeatherRestClient.get()
                .uri(uri -> uri
                        .queryParam("appid", getAppid())
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
