package com.me.weathertracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SignInController {

    @GetMapping("/sign-in")
    public String signIn() {
        return "auth/sign-in";
    }
}
