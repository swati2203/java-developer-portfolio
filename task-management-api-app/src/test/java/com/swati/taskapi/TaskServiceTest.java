package com.swati.taskapi;

import com.swati.taskapi.model.TaskStatus;
import com.swati.taskapi.repository.TaskRepository;
import com.swati.taskapi.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock TaskRepository taskRepository;
    @InjectMocks TaskService taskService;

    @Test
    void findAll_noStatus_returnsAll() {
        when(taskRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        var result = taskService.findAll(null, Pageable.unpaged());
        assertNotNull(result);
        verify(taskRepository).findAll(any(Pageable.class));
    }

    @Test
    void findAll_withStatus_filtersByStatus() {
        when(taskRepository.findByStatus(eq(TaskStatus.TODO), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        var result = taskService.findAll(TaskStatus.TODO, Pageable.unpaged());
        assertNotNull(result);
        verify(taskRepository).findByStatus(eq(TaskStatus.TODO), any(Pageable.class));
    }
}
