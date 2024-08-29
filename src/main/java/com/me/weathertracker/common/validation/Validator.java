package com.me.weathertracker.common.validation;

public interface Validator<T> {

    ValidationResult validate(T t);
}
