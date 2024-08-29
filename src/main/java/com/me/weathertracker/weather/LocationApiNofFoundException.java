package com.me.weathertracker.weather;

import com.me.weathertracker.common.exception.SmthWentWrongException;
import lombok.Getter;

@Getter
public class LocationApiNofFoundException extends SmthWentWrongException {

    public LocationApiNofFoundException(String name) {
        super("Issues with geocoding api for location '" + name + "'");
    }
}
