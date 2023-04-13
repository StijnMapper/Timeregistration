package com.example.backend.service;

import com.example.backend.model.Task;
import com.example.backend.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
}
