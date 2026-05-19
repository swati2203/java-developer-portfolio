package com.rahul.hrmanagement.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity @Table(name="departments") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(unique=true,nullable=false) private String name;
    private String description;
    private String location;
    @OneToMany(mappedBy="department") private List<Employee> employees;
}
