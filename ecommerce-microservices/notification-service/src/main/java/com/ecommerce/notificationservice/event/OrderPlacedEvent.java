package com.ecommerce.notificationservice.event;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor
public class OrderPlacedEvent {
    private String orderNumber;
    private String customerEmail;
}
