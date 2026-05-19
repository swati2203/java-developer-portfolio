# Task Management REST API — Spring Boot

## Tech Stack
- Java 17 · Spring Boot 3.2 · Spring Security · JWT · PostgreSQL · Swagger UI · Lombok

## Quick Start
```bash
# 1. Create DB
psql -U postgres -c "CREATE DATABASE taskdb;"

# 2. Update application.yml credentials

# 3. Run
mvn spring-boot:run
```

## Endpoints
| Method | URL | Auth | Description |
|--------|-----|------|-------------|
| POST | /api/auth/register | None | Register |
| POST | /api/auth/login | None | Login → JWT |
| GET | /api/tasks?status=TODO&page=0 | JWT | Paginated tasks |
| POST | /api/tasks | JWT | Create task |
| PUT | /api/tasks/{id} | JWT | Update task |
| DELETE | /api/tasks/{id} | Admin | Delete task |

Swagger UI → http://localhost:8080/swagger-ui.html
