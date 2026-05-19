# HR Management System — Spring Boot + PostgreSQL

## Features
- Employee management with hierarchical manager relationship
- Department management with payroll analytics
- Leave requests with approval workflow
- Flyway DB migrations (versioned schema)
- PostgreSQL window functions for salary ranking
- HikariCP connection pool (max 20)
- Testcontainers for integration tests with real PostgreSQL

## Tech Stack
- Java 17 · Spring Boot 3.2 · PostgreSQL 15
- Spring Data JPA · Flyway · QueryDSL
- HikariCP · Testcontainers · Lombok

## Quick Start
```bash
psql -U postgres -c "CREATE DATABASE hrdb;"
mvn spring-boot:run
```

## Key Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/employees?page=0&size=10 | Paginated employees |
| POST | /api/employees | Create employee |
| GET | /api/employees/search?department=Engineering&minSalary=500000&maxSalary=1500000 | Filter employees |
| GET | /api/departments | All departments |

## Database Schema
- departments (id, name, description, location)
- employees (id, first_name, last_name, email, salary, department_id, manager_id, ...)
- leave_requests (id, employee_id, leave_type, start_date, end_date, status)
