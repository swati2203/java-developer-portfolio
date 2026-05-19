package com.rahul.hrmanagement.controller;

import com.rahul.hrmanagement.dto.*;
import com.rahul.hrmanagement.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController @RequestMapping("/api/employees") @RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(employeeService.getAllEmployees(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(req));
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDto>> searchByDeptAndSalary(
            @RequestParam String department,
            @RequestParam BigDecimal minSalary,
            @RequestParam BigDecimal maxSalary) {
        return ResponseEntity.ok(employeeService.findByDeptAndSalary(department, minSalary, maxSalary));
    }
}
