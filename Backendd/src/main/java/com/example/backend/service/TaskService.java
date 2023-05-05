package com.example.backend.service;

import com.example.backend.model.Task;
import com.example.backend.model.TimeRegistration;
import com.example.backend.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private TimeRegistrationService timeRegistrationService;
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(int taskId) {
        return taskRepository.findById(taskId).orElseThrow(() ->  new NullPointerException("Task not found with id " + taskId));
    }


    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public boolean deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
        return false;
    }


}
