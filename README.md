# The weather tracker project

<img src="/img/home-page-example.png">
<img src="/img/search-result-example.png">

## Overview

Web application for viewing current weather.
Users can register and add one or more locations to the collection.
The main page of the application then displays the current weather and a list of the user's locations.

## Infrastructure

### Technologies

- Backend
  - Spring Boot, Security
  - Thymeleaf
- DB
  - PostgreSQL
  - Hibernate
- Frontend
  - HTML/CSS, Bootstrap
- Testing
  - JUnit5, Mockito
  - AssertJ

---

### Database
The project use PostgreSQL as DB for storage users.

docker run:

``
docker run --name weather-db -p 5438:5432 -e POSTGRES_PASSWORD=12345 -e POSTGRES_DB=weather-db postgres:16
``