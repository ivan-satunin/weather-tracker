package com.me.weathertracker.common.exception.handling;

import com.me.weathertracker.common.exception.SmthWentWrongException;
import com.me.weathertracker.common.exception.ValidationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {

    @ExceptionHandler
    public String handleSmthWentWrongException(SmthWentWrongException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "errors/single-msg-error";
    }

    @ExceptionHandler
    public String handleValidationException(ValidationException ex, Model model) {
        model.addAttribute("errors", ex.getErrors());
        return ex.getRedirectPage();
    }
}
