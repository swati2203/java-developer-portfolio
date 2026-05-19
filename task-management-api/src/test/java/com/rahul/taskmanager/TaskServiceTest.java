package com.rahul.taskmanager;

import com.rahul.taskmanager.dto.TaskDto;
import com.rahul.taskmanager.dto.TaskRequest;
import com.rahul.taskmanager.exception.ResourceNotFoundException;
import com.rahul.taskmanager.model.Task;
import com.rahul.taskmanager.model.TaskStatus;
import com.rahul.taskmanager.model.Priority;
import com.rahul.taskmanager.repository.TaskRepository;
import com.rahul.taskmanager.repository.UserRepository;
import com.rahul.taskmanager.service.EmailService;
import com.rahul.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
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

    @Mock private TaskRepository taskRepository;
    @Mock private UserRepository userRepository;
    @Mock private EmailService emailService;

    @InjectMocks private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = Task.builder()
                .id(1L).title("Fix login bug")
                .status(TaskStatus.TODO).priority(Priority.HIGH)
                .build();
    }

    @Test
    void getTaskById_found_returnsDto() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        TaskDto dto = taskService.getTaskById(1L);
        assertNotNull(dto);
        assertEquals("Fix login bug", dto.getTitle());
    }

    @Test
    void getTaskById_notFound_throwsException() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> taskService.getTaskById(99L));
    }

    @Test
    void deleteTask_notFound_throwsException() {
        when(taskRepository.existsById(99L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> taskService.deleteTask(99L));
    }
}
