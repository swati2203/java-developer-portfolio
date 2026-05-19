package com.rahul.hrmanagement.repository;

import com.rahul.hrmanagement.model.Employee;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByDepartmentId(Long deptId, Pageable pageable);

    @Query("""
        SELECT e FROM Employee e
        JOIN FETCH e.department d
        WHERE d.name = :deptName
        AND e.salary BETWEEN :minSalary AND :maxSalary
        ORDER BY e.salary DESC
    """)
    List<Employee> findByDeptAndSalaryRange(String deptName, BigDecimal minSalary, BigDecimal maxSalary);

    @Query("""
        SELECT d.name as department,
               COUNT(e) as headcount,
               AVG(e.salary) as avgSalary,
               MAX(e.salary) as maxSalary,
               MIN(e.salary) as minSalary
        FROM Employee e JOIN e.department d
        GROUP BY d.name
        ORDER BY headcount DESC
    """)
    List<Object[]> getDepartmentPayrollStats();

    @Query(value = """
        SELECT e.*, 
               RANK() OVER (PARTITION BY e.department_id ORDER BY e.salary DESC) as salary_rank
        FROM employees e
    """, nativeQuery = true)
    List<Object[]> getEmployeesWithSalaryRank();

    boolean existsByEmail(String email);
}
