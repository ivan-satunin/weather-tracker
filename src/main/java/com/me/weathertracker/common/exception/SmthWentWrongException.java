package com.me.weathertracker.common.exception;

public class SmthWentWrongException extends RuntimeException {

    public SmthWentWrongException(String message) {
        super(message);
    }

    public SmthWentWrongException() {
        this("Oops something went wrong");
    }
}
