package com.rahul.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="tasks") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Task {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String title;
    @Column(columnDefinition="TEXT") private String description;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private TaskStatus status;
    @Enumerated(EnumType.STRING) private Priority priority;
    @Column(name="due_date") private LocalDateTime dueDate;
    @Column(name="created_at",updatable=false) private LocalDateTime createdAt;
    @Column(name="updated_at") private LocalDateTime updatedAt;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id") private User assignedTo;

    @PrePersist protected void onCreate() { createdAt = updatedAt = LocalDateTime.now(); }
    @PreUpdate  protected void onUpdate() { updatedAt = LocalDateTime.now(); }
}
