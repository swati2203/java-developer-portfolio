package com.rahul.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity @Table(name="users") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(unique=true,nullable=false) private String email;
    @Column(nullable=false) private String password;
    private String fullName;
    @Enumerated(EnumType.STRING) private Role role;
    @OneToMany(mappedBy="assignedTo",cascade=CascadeType.ALL) private List<Task> tasks;
}
