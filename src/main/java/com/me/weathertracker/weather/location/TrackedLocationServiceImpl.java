package com.me.weathertracker.weather.location;

import com.me.weathertracker.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Location track(String locationName, BigDecimal latitude, BigDecimal longitude) {
        final var maybeLocation = trackedLocationRepository.findByNameCoordAndUser(latitude, longitude, SecurityUtils.currentUser());
        if (maybeLocation.isPresent())
            return maybeLocation.get();

        final var location = Location.builder()
                .name(locationName)
                .latitude(latitude)
                .longitude(longitude)
                .user(SecurityUtils.currentUser())
                .build();
        return trackedLocationRepository.save(location);
    }

    @Override
    @Transactional
    public void stopTrack(BigDecimal latitude, BigDecimal longitude) {
        trackedLocationRepository.deleteByCoordAndUser(latitude, longitude, SecurityUtils.currentUser());
    }
}
