package com.ecommerce.orderservice.event;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor
public class OrderPlacedEvent {
    private String orderNumber;
    private String customerEmail;
}
