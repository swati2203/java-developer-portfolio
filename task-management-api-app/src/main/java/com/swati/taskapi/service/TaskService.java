package com.swati.taskapi.service;

import com.swati.taskapi.dto.TaskRequest;
import com.swati.taskapi.dto.TaskResponse;
import com.swati.taskapi.exception.ResourceNotFoundException;
import com.swati.taskapi.model.Task;
import com.swati.taskapi.model.TaskStatus;
import com.swati.taskapi.repository.TaskRepository;
import com.swati.taskapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Page<TaskResponse> findAll(TaskStatus status, Pageable pageable) {
        Page<Task> tasks = (status != null)
                ? taskRepository.findByStatus(status, pageable)
                : taskRepository.findAll(pageable);
        return tasks.map(this::toResponse);
    }

    public TaskResponse findById(Long id) {
        return toResponse(taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id)));
    }

    @Transactional
    public TaskResponse create(TaskRequest req) {
        Task task = Task.builder()
                .title(req.getTitle()).description(req.getDescription())
                .status(req.getStatus()).priority(req.getPriority())
                .dueDate(req.getDueDate()).build();
        if (req.getAssignedToUserId() != null) {
            task.setAssignedTo(userRepository.findById(req.getAssignedToUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found")));
        }
        return toResponse(taskRepository.save(task));
    }

    @Transactional
    public TaskResponse update(Long id, TaskRequest req) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        task.setTitle(req.getTitle()); task.setDescription(req.getDescription());
        task.setStatus(req.getStatus()); task.setPriority(req.getPriority());
        task.setDueDate(req.getDueDate());
        return toResponse(taskRepository.save(task));
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) throw new ResourceNotFoundException("Task not found: " + id);
        taskRepository.deleteById(id);
    }

    private TaskResponse toResponse(Task t) {
        return TaskResponse.builder()
                .id(t.getId()).title(t.getTitle()).description(t.getDescription())
                .status(t.getStatus()).priority(t.getPriority())
                .dueDate(t.getDueDate()).createdAt(t.getCreatedAt())
                .assignedTo(t.getAssignedTo() != null ? t.getAssignedTo().getEmail() : null)
                .build();
    }
}
