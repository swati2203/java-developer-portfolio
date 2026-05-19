package com.ecommerce.inventoryservice.model;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name="inventory") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Inventory {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(unique=true,nullable=false) private String skuCode;
    private Integer quantity;
}
