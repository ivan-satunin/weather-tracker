CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    login    varchar UNIQUE NOT NULL,
    password varchar        NOT NULL
);

CREATE UNIQUE INDEX unique_users_login_idx ON users (login);

CREATE TABLE IF NOT EXISTS location
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR NOT NULL,
    user_id   INT REFERENCES users,
    latitude  DECIMAL NOT NULL,
    longitude DECIMAL NOT NULL
);