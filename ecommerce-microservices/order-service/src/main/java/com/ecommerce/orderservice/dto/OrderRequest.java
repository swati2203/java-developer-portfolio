package com.ecommerce.orderservice.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor
public class OrderRequest {
    @Email @NotBlank private String customerEmail;
    @NotEmpty private List<OrderItemDto> items;
}
