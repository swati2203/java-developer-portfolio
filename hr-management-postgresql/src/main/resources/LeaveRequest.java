package com.rahul.hrmanagement.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity @Table(name="leave_requests") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LeaveRequest {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="employee_id") private Employee employee;
    @Enumerated(EnumType.STRING) private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING) private LeaveStatus status;
    private String reason;
    @Column(name="applied_at") private LocalDateTime appliedAt;
    @PrePersist protected void onCreate() { appliedAt = LocalDateTime.now(); }
}
