package com.me.weathertracker.weather.location;

import com.me.weathertracker.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TrackedLocationServiceImpl implements TrackedLocationService {
    private final TrackedLocationRepository trackedLocationRepository;

    @Override
    public List<Location> findAll() {
        return trackedLocationRepository.findAll();
    }

    @Override
    public Location track(String name, BigDecimal latitude, BigDecimal longitude) {
        final var maybeLocation = trackedLocationRepository.findByNameCoordAndUser(name, latitude, longitude, SecurityUtils.currentUser());
        if (maybeLocation.isPresent())
            return maybeLocation.get();

        final var location = Location.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .user(SecurityUtils.currentUser())
                .build();
        return trackedLocationRepository.save(location);
    }
}
