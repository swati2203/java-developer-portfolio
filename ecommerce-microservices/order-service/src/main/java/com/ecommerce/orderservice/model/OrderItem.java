package com.ecommerce.orderservice.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity @Table(name="order_items") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String skuCode;
    private Integer quantity;
    private BigDecimal price;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id") private Order order;
}
