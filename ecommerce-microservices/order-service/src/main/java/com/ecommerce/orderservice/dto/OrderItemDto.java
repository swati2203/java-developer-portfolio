package com.ecommerce.orderservice.dto;
import lombok.*;
import java.math.BigDecimal;
@Data @NoArgsConstructor @AllArgsConstructor
public class OrderItemDto {
    private String skuCode;
    private Integer quantity;
    private BigDecimal price;
}
