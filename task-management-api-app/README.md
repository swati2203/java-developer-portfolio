# Task Management REST API

Spring Boot 3 REST API with JWT auth, role-based access, Swagger UI and async email notifications.

## Tech Stack
- Java 17 + Spring Boot 3.2
- Spring Security 6 + JWT (jjwt 0.11.5)
- Spring Data JPA + H2 (dev) / PostgreSQL (prod)
- SpringDoc OpenAPI (Swagger UI at /swagger-ui.html)
- JUnit 5 + Mockito

## Run
```bash
mvn spring-boot:run
# Swagger UI: http://localhost:8080/swagger-ui.html
# H2 Console: http://localhost:8080/h2-console
```

## API Endpoints
| Method | URL                    | Description         | Auth     |
|--------|------------------------|---------------------|----------|
| GET    | /api/v1/tasks          | List tasks (paged)  | Bearer   |
| GET    | /api/v1/tasks/{id}     | Get task            | Bearer   |
| POST   | /api/v1/tasks          | Create task         | Bearer   |
| PUT    | /api/v1/tasks/{id}     | Update task         | Bearer   |
| DELETE | /api/v1/tasks/{id}     | Delete task         | ADMIN    |
