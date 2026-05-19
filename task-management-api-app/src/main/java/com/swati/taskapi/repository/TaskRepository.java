package com.swati.taskapi.repository;

import com.swati.taskapi.model.Task;
import com.swati.taskapi.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByStatus(TaskStatus status, Pageable pageable);
    Page<Task> findByAssignedToId(Long userId, Pageable pageable);
    @Query("SELECT t FROM Task t WHERE t.assignedTo.id = :userId AND t.status = :status")
    Page<Task> findByUserAndStatus(@Param("userId") Long userId, @Param("status") TaskStatus status, Pageable pageable);
    long countByStatus(TaskStatus status);
}
