package com.swati.taskapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "users")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Role role;
}
