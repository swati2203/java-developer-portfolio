package com.ecommerce.notificationservice.listener;
import com.ecommerce.notificationservice.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component @Slf4j
public class NotificationListener {
    @KafkaListener(topics="order-placed", groupId="notification-group")
    public void handleOrderPlaced(OrderPlacedEvent event) {
        log.info("Sending order confirmation email to {} for order {}", 
            event.getCustomerEmail(), event.getOrderNumber());
        // In production: use JavaMailSender here
    }
}
