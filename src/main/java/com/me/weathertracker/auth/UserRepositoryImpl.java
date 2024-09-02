package com.me.weathertracker.auth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String FIND_BY_LOGIN_HQL = """
            SELECT u FROM AppUser u
            WHERE u.login = :login
            """;

    private final EntityManager entityManager;

    @Override
    public Optional<AppUser> findByLogin(String login) {
        try {
            final var resultUser = entityManager.createQuery(FIND_BY_LOGIN_HQL, AppUser.class)
                    .setParameter("login", login).getSingleResult();
            return Optional.of(resultUser);
        } catch (NoResultException noResultException) {
            return Optional.empty();
        }
    }

    @Override
    public AppUser save(AppUser user) {
        entityManager.persist(user);
        return user;
    }
}
