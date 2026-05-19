package com.ecommerce.orderservice.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Entity @Table(name="orders") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(unique=true) private String orderNumber;
    @Enumerated(EnumType.STRING) private OrderStatus status;
    private BigDecimal totalAmount;
    private String customerEmail;
    @OneToMany(mappedBy="order",cascade=CascadeType.ALL,fetch=FetchType.EAGER) private List<OrderItem> items;
    @Column(name="created_at") private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); }
}
