package com.me.weathertracker.weather.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/weather-tracker/track")
public class TrackedLocationController {
    private final TrackedLocationService trackedLocationService;

    @PostMapping
    public String createLocation(
            @RequestParam String q,
            @RequestParam BigDecimal lon,
            @RequestParam BigDecimal lat
    ) {
        trackedLocationService.track(q, lat, lon);
        return "redirect:/weather-tracker";
    }

    @PostMapping("stop")
    public String stopTrackLocation(
            @RequestParam BigDecimal lat,
            @RequestParam BigDecimal lon
    ) {
        trackedLocationService.stopTrack(lat, lon);
        return "redirect:/weather-tracker";
    }
}
