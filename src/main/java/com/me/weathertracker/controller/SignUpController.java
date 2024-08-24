package com.me.weathertracker.controller;

import com.me.weathertracker.service.AppUserService;
import com.me.weathertracker.controller.payload.NewUserPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/sign-up")
public class SignUpController {
    private final AppUserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public String signUp() {
        return "auth/sign-up";
    }

    @PostMapping
    public String createUser(NewUserPayload newUserPayload) {
        userService.register(newUserPayload.login(), newUserPayload.password());

        return "redirect:";
    }
}
