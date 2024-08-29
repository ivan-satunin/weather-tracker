package com.me.weathertracker.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/weather-tracker")
public class HomeController {
    private final WeatherService weatherService;

    @GetMapping
    public String homePage(Model model) {
        final var weathers = weatherService.findWeathersForTrackedLocations();
        model.addAttribute("weathers", weathers);
        return "index";
    }
}
