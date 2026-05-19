package com.rahul.hrmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name="employees") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Employee {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;

    @Column(nullable=false) private String firstName;
    @Column(nullable=false) private String lastName;
    @Column(unique=true,nullable=false) private String email;
    private String phone;
    private String jobTitle;
    private BigDecimal salary;
    private LocalDate hireDate;

    @Enumerated(EnumType.STRING) private EmploymentStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="department_id") private Department department;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="manager_id") private Employee manager;

    @Column(name="created_at", updatable=false) private LocalDateTime createdAt;
    @Column(name="updated_at") private LocalDateTime updatedAt;

    @PrePersist protected void onCreate() { createdAt = updatedAt = LocalDateTime.now(); }
    @PreUpdate  protected void onUpdate() { updatedAt = LocalDateTime.now(); }
}
