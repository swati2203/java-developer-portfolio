package com.rahul.taskapi;

import com.rahul.taskapi.dto.TaskDto;
import com.rahul.taskapi.exception.ResourceNotFoundException;
import com.rahul.taskapi.model.Task;
import com.rahul.taskapi.repository.TaskRepository;
import com.rahul.taskapi.service.EmailService;
import com.rahul.taskapi.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock TaskRepository taskRepository;
    @Mock EmailService emailService;
    @InjectMocks TaskService taskService;

    @Test
    void findById_shouldReturnTask_whenExists() {
        Task task = Task.builder().id(1L).title("Write unit tests")
                .status(Task.TaskStatus.TODO).build();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskDto result = taskService.findById(1L);

        assertEquals("Write unit tests", result.getTitle());
        verify(taskRepository).findById(1L);
    }

    @Test
    void findById_shouldThrow_whenNotFound() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> taskService.findById(99L));
    }
}
