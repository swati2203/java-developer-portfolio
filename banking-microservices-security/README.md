# Banking Transaction System — Microservices + Spring Security

## Architecture
```
Client --> Auth Service (8090)  --> issues JWT
Client --> Account Service (8091) --> validates JWT + Redis cache
Client --> Transaction Service (8092) --> validates JWT + PostgreSQL
```

## Services
| Service | Port | Description |
|---------|------|-------------|
| auth-service | 8090 | JWT token issuer, user auth |
| account-service | 8091 | Account CRUD, balance management |
| transaction-service | 8092 | Fund transfers, transaction history |

## Quick Start
```bash
# 1. Start infrastructure
docker run -d --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres:15
docker run -d --name redis -p 6379:6379 redis:7

# 2. Create databases
psql -U postgres -c "CREATE DATABASE authdb; CREATE DATABASE bankdb;"

# 3. Start services (separate terminals)
cd auth-service && mvn spring-boot:run
cd account-service && mvn spring-boot:run
cd transaction-service && mvn spring-boot:run

# 4. Login
curl -X POST http://localhost:8090/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"pass"}'

# 5. Use token
curl -H "Authorization: Bearer <TOKEN>" http://localhost:8091/api/accounts/my

# 6. Transfer funds
curl -X POST "http://localhost:8092/api/transactions/transfer?fromAccount=ACC1&toAccount=ACC2&amount=1000" \
  -H "Authorization: Bearer <TOKEN>"
```
