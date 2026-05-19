package com.rahul.hrmanagement.repository;
import com.rahul.hrmanagement.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);
    List<LeaveRequest> findByStatus(LeaveStatus status);
}
