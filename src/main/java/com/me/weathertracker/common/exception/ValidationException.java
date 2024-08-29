package com.me.weathertracker.common.exception;

import com.me.weathertracker.common.validation.Error;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final List<Error> errors;
    private final String redirectPage;

    public ValidationException(List<Error> errors, String redirectPage) {
        this.errors = errors;
        this.redirectPage = redirectPage;
    }
}
