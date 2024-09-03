package com.me.weathertracker.weather.location;

import com.me.weathertracker.auth.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TrackedLocationRepositoryImpl implements TrackedLocationRepository {
    private static final String FIND_ALL_LOCATIONS_HQL = """
            SELECT loc FROM Location loc
            """;
    private static final String FIND_BY_NAME_COORD_AND_USER = """
            SELECT loc FROM Location loc
            WHERE loc.user = :user AND loc.longitude = :lon AND loc.latitude = :lat
            """;
    private final EntityManager entityManager;

    @Override
    public Optional<Location> findByNameCoordAndUser(BigDecimal latitude, BigDecimal longitude, AppUser user) {
        try {
            final var location = entityManager.createQuery(FIND_BY_NAME_COORD_AND_USER, Location.class)
                    .setParameter("user", user)
                    .setParameter("lat", latitude)
                    .setParameter("lon", longitude)
                    .getSingleResult();
            return Optional.of(location);
        } catch (NoResultException noResultException) {
            return Optional.empty();
        }
    }

    @Override
    public List<Location> findAll() {
        return entityManager.createQuery(FIND_ALL_LOCATIONS_HQL, Location.class)
                .getResultList();
    }

    @Override
    public Location save(Location location) {
        entityManager.persist(location);
        entityManager.flush();
        return location;
    }

    @Override
    public void deleteByCoordAndUser(BigDecimal latitude, BigDecimal longitude, AppUser user) {
        var maybeLocation = findByNameCoordAndUser(latitude, longitude, user);
        maybeLocation.ifPresent(entityManager::remove);
    }
}
