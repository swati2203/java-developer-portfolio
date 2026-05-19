package com.rahul.hrmanagement.dto;

import com.rahul.hrmanagement.model.EmploymentStatus;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class EmployeeRequest {
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @Email @NotBlank private String email;
    private String phone;
    private String jobTitle;
    @DecimalMin("0.0") private BigDecimal salary;
    private LocalDate hireDate;
    private EmploymentStatus status;
    private Long departmentId;
    private Long managerId;
}
