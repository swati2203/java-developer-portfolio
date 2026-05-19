package com.rahul.taskmanager.repository;

import com.rahul.taskmanager.model.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByStatus(TaskStatus status, Pageable pageable);
    Page<Task> findByAssignedToId(Long userId, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.assignedTo.id=:userId AND t.status=:status")
    List<Task> findByUserAndStatus(Long userId, TaskStatus status);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.status=:status")
    long countByStatus(TaskStatus status);
}
