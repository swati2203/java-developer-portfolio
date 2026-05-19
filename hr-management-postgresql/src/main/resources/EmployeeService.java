package com.rahul.hrmanagement.service;

import com.rahul.hrmanagement.dto.*;
import com.rahul.hrmanagement.exception.ResourceNotFoundException;
import com.rahul.hrmanagement.model.*;
import com.rahul.hrmanagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service @RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public Page<EmployeeDto> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(this::toDto);
    }

    public EmployeeDto getById(Long id) {
        return toDto(employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id)));
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeRequest req) {
        if (employeeRepository.existsByEmail(req.getEmail()))
            throw new IllegalArgumentException("Email already in use: " + req.getEmail());

        Employee emp = Employee.builder()
                .firstName(req.getFirstName()).lastName(req.getLastName())
                .email(req.getEmail()).phone(req.getPhone()).jobTitle(req.getJobTitle())
                .salary(req.getSalary()).hireDate(req.getHireDate()).status(req.getStatus()).build();

        if (req.getDepartmentId() != null) {
            emp.setDepartment(departmentRepository.findById(req.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found")));
        }
        if (req.getManagerId() != null) {
            emp.setManager(employeeRepository.findById(req.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found")));
        }
        return toDto(employeeRepository.save(emp));
    }

    public List<EmployeeDto> findByDeptAndSalary(String dept, BigDecimal min, BigDecimal max) {
        return employeeRepository.findByDeptAndSalaryRange(dept, min, max).stream().map(this::toDto).toList();
    }

    private EmployeeDto toDto(Employee e) {
        return EmployeeDto.builder()
                .id(e.getId()).firstName(e.getFirstName()).lastName(e.getLastName())
                .email(e.getEmail()).phone(e.getPhone()).jobTitle(e.getJobTitle())
                .salary(e.getSalary()).hireDate(e.getHireDate()).status(e.getStatus())
                .departmentName(e.getDepartment() != null ? e.getDepartment().getName() : null)
                .managerName(e.getManager() != null ?
                        e.getManager().getFirstName() + " " + e.getManager().getLastName() : null)
                .build();
    }
}
