package com.rahul.hrmanagement;

import com.rahul.hrmanagement.model.*;
import com.rahul.hrmanagement.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("hrdb_test")
            .withUsername("test")
            .withPassword("test");

    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private DepartmentRepository departmentRepository;

    @Test
    void saveAndFindEmployee() {
        Department dept = departmentRepository.save(
                Department.builder().name("QA").description("Quality Assurance").build());

        Employee emp = Employee.builder()
                .firstName("Anjali").lastName("Mehta")
                .email("anjali@test.com").salary(new BigDecimal("750000"))
                .hireDate(LocalDate.now()).status(EmploymentStatus.ACTIVE)
                .department(dept).build();

        Employee saved = employeeRepository.save(emp);

        assertNotNull(saved.getId());
        assertEquals("Anjali", saved.getFirstName());
        assertEquals("QA", saved.getDepartment().getName());
    }

    @Test
    void existsByEmail_returnsTrue() {
        departmentRepository.save(Department.builder().name("IT").description("IT Dept").build());
        Employee emp = Employee.builder().firstName("Raj").lastName("Kumar")
                .email("raj@test.com").status(EmploymentStatus.ACTIVE).build();
        employeeRepository.save(emp);
        assertTrue(employeeRepository.existsByEmail("raj@test.com"));
    }
}
