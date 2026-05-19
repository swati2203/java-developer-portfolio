# E-Commerce Microservices Platform

## Architecture
```
Client → API Gateway (8080)
           ├── Product Service  (8082) → PostgreSQL
           ├── Inventory Service (8083) → PostgreSQL
           └── Order Service    (8081) → PostgreSQL
                      ↓ Kafka (order-placed topic)
                 Notification Service (8084)
```

## Services
| Service | Port | Description |
|---------|------|-------------|
| api-gateway | 8080 | Spring Cloud Gateway |
| order-service | 8081 | Place orders, Kafka producer |
| product-service | 8082 | Product catalog |
| inventory-service | 8083 | Stock management |
| notification-service | 8084 | Kafka consumer, sends emails |

## Quick Start
```bash
# Start all services
docker-compose up -d

# Place an order
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"customerEmail":"user@test.com","items":[{"skuCode":"PHONE-001","quantity":1,"price":999.99}]}'
```
