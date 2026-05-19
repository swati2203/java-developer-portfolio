package com.ecommerce.orderservice.controller;
import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/orders") @RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<Order> placeOrder(@Valid @RequestBody OrderRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(req));
    }
}
