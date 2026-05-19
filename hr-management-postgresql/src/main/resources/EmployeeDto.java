package com.rahul.hrmanagement.dto;

import com.rahul.hrmanagement.model.EmploymentStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String jobTitle;
    private BigDecimal salary;
    private LocalDate hireDate;
    private EmploymentStatus status;
    private String departmentName;
    private String managerName;
}
