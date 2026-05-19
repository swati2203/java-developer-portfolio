package com.swati.taskapi.dto;

import com.swati.taskapi.model.Priority;
import com.swati.taskapi.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskRequest {
    @NotBlank(message = "Title is required") private String title;
    private String description;
    @NotNull private TaskStatus status;
    @NotNull private Priority priority;
    private LocalDateTime dueDate;
    private Long assignedToUserId;
}
