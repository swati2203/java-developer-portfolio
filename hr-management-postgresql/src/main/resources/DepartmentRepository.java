package com.rahul.hrmanagement.repository;
import com.rahul.hrmanagement.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
