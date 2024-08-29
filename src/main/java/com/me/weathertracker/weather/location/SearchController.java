package com.me.weathertracker.weather.location;

import com.me.weathertracker.weather.SearchLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/weather-tracker/search")
public class SearchController {
    private final SearchLocationService searchLocationService;

    @GetMapping
    public String getLocations(@RequestParam String q, Model model) {
        final var location = searchLocationService.find(q);
        model.addAttribute("location", location);
        return "locations/search-result";
    }
}
