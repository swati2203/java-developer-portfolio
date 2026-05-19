package com.rahul.taskmanager.dto;

import com.rahul.taskmanager.model.Priority;
import com.rahul.taskmanager.model.TaskStatus;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
public class TaskRequest {
    @NotBlank(message="Title is required") private String title;
    private String description;
    @NotNull(message="Status is required") private TaskStatus status;
    private Priority priority;
    private LocalDateTime dueDate;
    private Long assignedToUserId;
}
