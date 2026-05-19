package com.rahul.taskmanager.dto;

import com.rahul.taskmanager.model.Priority;
import com.rahul.taskmanager.model.TaskStatus;
import lombok.*;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private String assignedTo;
}
