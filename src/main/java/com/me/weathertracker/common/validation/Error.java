package com.me.weathertracker.common.validation;

import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
    String message;
}
