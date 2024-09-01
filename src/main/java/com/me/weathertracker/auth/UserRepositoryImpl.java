package com.me.weathertracker.auth;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String FIND_BY_LOGIN_HQL = """
            SELECT u FROM AppUser u
            WHERE u.login = :login
            """;

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<AppUser> findByLogin(String login) {
        EntityTransaction transaction = null;
        try (var session = entityManagerFactory.createEntityManager()) {
            transaction = session.getTransaction();
            transaction.begin();
            final var resultUser = session.createQuery(FIND_BY_LOGIN_HQL, AppUser.class)
                    .setParameter("login", login).getSingleResult();
            return Optional.of(resultUser);
        } catch (NoResultException noResultException) {
            return Optional.empty();
        } finally {
            if (transaction != null)
                transaction.commit();
        }
    }

    @Override
    public AppUser save(AppUser user) {
        try (var session = entityManagerFactory.createEntityManager()) {
            final var transaction = session.getTransaction();
            transaction.begin();

            session.persist(user);

            transaction.commit();
            return user;
        }
    }
}
