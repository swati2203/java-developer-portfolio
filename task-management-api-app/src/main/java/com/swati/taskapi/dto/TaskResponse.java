package com.swati.taskapi.dto;

import com.swati.taskapi.model.Priority;
import com.swati.taskapi.model.TaskStatus;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data @Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private String assignedTo;
}
