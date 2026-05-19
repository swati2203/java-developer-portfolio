package com.rahul.taskmanager.controller;

import com.rahul.taskmanager.dto.*;
import com.rahul.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/tasks") @RequiredArgsConstructor
@SecurityRequirement(name="bearerAuth") @Tag(name="Tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary="Get all tasks (paginated, filterable by status)")
    public ResponseEntity<Page<TaskDto>> getAll(
            @RequestParam(required=false) String status, Pageable pageable) {
        return ResponseEntity.ok(taskService.getAllTasks(status, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@Valid @RequestBody TaskRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @Valid @RequestBody TaskRequest req) {
        return ResponseEntity.ok(taskService.updateTask(id, req));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
