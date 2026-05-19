package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.client.InventoryClient;
import com.ecommerce.orderservice.dto.*;
import com.ecommerce.orderservice.event.OrderPlacedEvent;
import com.ecommerce.orderservice.model.*;
import com.ecommerce.orderservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor @Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Transactional
    @CircuitBreaker(name="inventory", fallbackMethod="fallbackOrder")
    public Order placeOrder(OrderRequest req) {
        List<String> skus = req.getItems().stream().map(OrderItemDto::getSkuCode).toList();
        if (!inventoryClient.checkStock(skus)) throw new IllegalStateException("Out of stock");

        List<OrderItem> items = req.getItems().stream().map(i ->
            OrderItem.builder().skuCode(i.getSkuCode()).quantity(i.getQuantity()).price(i.getPrice()).build()
        ).toList();

        BigDecimal total = req.getItems().stream()
            .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder().orderNumber(UUID.randomUUID().toString())
            .status(OrderStatus.CONFIRMED).customerEmail(req.getCustomerEmail())
            .totalAmount(total).items(items).build();
        items.forEach(i -> i.setOrder(order));
        Order saved = orderRepository.save(order);
        kafkaTemplate.send("order-placed", new OrderPlacedEvent(saved.getOrderNumber(), saved.getCustomerEmail()));
        log.info("Order placed: {}", saved.getOrderNumber());
        return saved;
    }

    public Order fallbackOrder(OrderRequest req, Exception ex) {
        log.warn("Inventory unavailable: {}", ex.getMessage());
        throw new RuntimeException("Inventory service unavailable. Try again later.");
    }
}
