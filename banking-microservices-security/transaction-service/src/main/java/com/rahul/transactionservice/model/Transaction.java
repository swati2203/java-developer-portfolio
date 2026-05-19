package com.rahul.transactionservice.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name="transactions") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Transaction {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING) private TransactionType type;
    @Enumerated(EnumType.STRING) private TransactionStatus status;
    private String description;
    @Column(name="created_at") private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); }
}
