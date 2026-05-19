package com.rahul.accountservice.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name="accounts") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(unique=true,nullable=false) private String accountNumber;
    @Column(nullable=false) private String ownerUsername;
    @Enumerated(EnumType.STRING) private AccountType accountType;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING) private AccountStatus status;
    @Column(name="created_at") private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); }
}
