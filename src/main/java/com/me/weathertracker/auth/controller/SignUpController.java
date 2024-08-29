package com.me.weathertracker.auth.controller;

import com.me.weathertracker.auth.AppUserService;
import com.me.weathertracker.auth.controller.payload.NewUserPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/weather-tracker/sign-up")
public class SignUpController {
    private final AppUserService userService;

    @GetMapping
    public String signUp() {
        return "auth/sign-up";
    }

    @PostMapping
    public String createUser(NewUserPayload newUserPayload) {
        userService.register(newUserPayload.login(), newUserPayload.password());

        return "redirect:/weather-tracker";
    }
}
