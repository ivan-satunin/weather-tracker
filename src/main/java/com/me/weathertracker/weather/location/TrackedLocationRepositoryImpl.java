package com.me.weathertracker.weather.location;

import com.me.weathertracker.auth.AppUser;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
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
            WHERE loc.name = :name AND loc.user = :user AND loc.longitude = :lon AND loc.latitude = :lat
            """;
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<Location> findByNameCoordAndUser(String name, BigDecimal latitude, BigDecimal longitude, AppUser user) {
        EntityTransaction transaction = null;
        try (var session = entityManagerFactory.createEntityManager()) {
            transaction = session.getTransaction();
            transaction.begin();
            final var location = session.createQuery(FIND_BY_NAME_COORD_AND_USER, Location.class)
                    .setParameter("name", name)
                    .setParameter("user", user)
                    .setParameter("lat", latitude)
                    .setParameter("lon", longitude)
                    .getSingleResult();
            session.flush();
            transaction.commit();
            return Optional.of(location);
        } catch (NoResultException noResultException) {
            if (transaction != null)
                transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Location> findAll() {
        try (var session = entityManagerFactory.createEntityManager()) {
            final var transaction = session.getTransaction();
            transaction.begin();

            final var locations = session.createQuery(FIND_ALL_LOCATIONS_HQL, Location.class).getResultList();
            session.flush();
            transaction.commit();
            return locations;
        }
    }

    @Override
    public Location save(Location location) {
        try (var session = entityManagerFactory.createEntityManager()) {
            final var transaction = session.getTransaction();
            transaction.begin();

            session.persist(location);
            session.flush();

            transaction.commit();
            return location;
        }
    }
}
