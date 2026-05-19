package com.rahul.taskmanager.service;

import com.rahul.taskmanager.dto.*;
import com.rahul.taskmanager.exception.ResourceNotFoundException;
import com.rahul.taskmanager.model.*;
import com.rahul.taskmanager.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public Page<TaskDto> getAllTasks(String status, Pageable pageable) {
        if (status != null) {
            return taskRepository.findByStatus(TaskStatus.valueOf(status.toUpperCase()), pageable).map(this::toDto);
        }
        return taskRepository.findAll(pageable).map(this::toDto);
    }

    public TaskDto getTaskById(Long id) {
        return toDto(taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id)));
    }

    @Transactional
    public TaskDto createTask(TaskRequest req) {
        Task task = Task.builder()
                .title(req.getTitle()).description(req.getDescription())
                .status(req.getStatus()).priority(req.getPriority()).dueDate(req.getDueDate())
                .build();
        if (req.getAssignedToUserId() != null) {
            var user = userRepository.findById(req.getAssignedToUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            task.setAssignedTo(user);
            notifyAsync(user.getEmail(), task.getTitle());
        }
        return toDto(taskRepository.save(task));
    }

    @Transactional
    public TaskDto updateTask(Long id, TaskRequest req) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        task.setTitle(req.getTitle()); task.setDescription(req.getDescription());
        task.setStatus(req.getStatus()); task.setPriority(req.getPriority());
        task.setDueDate(req.getDueDate());
        return toDto(taskRepository.save(task));
    }

    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) throw new ResourceNotFoundException("Task not found: " + id);
        taskRepository.deleteById(id);
    }

    @Async
    protected void notifyAsync(String email, String title) { emailService.sendTaskAssignmentEmail(email, title); }

    private TaskDto toDto(Task t) {
        return TaskDto.builder().id(t.getId()).title(t.getTitle()).description(t.getDescription())
                .status(t.getStatus()).priority(t.getPriority()).dueDate(t.getDueDate())
                .createdAt(t.getCreatedAt())
                .assignedTo(t.getAssignedTo() != null ? t.getAssignedTo().getEmail() : null).build();
    }
}
