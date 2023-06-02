package com.example.backend.service;

import com.example.backend.model.Task;
import com.example.backend.repository.TaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

        Task createdTask = taskService.createTask(new Task());

        Assertions.assertNotNull(createdTask);
    }


    @Test
    void createTask_happyPath() {
        Task task = new Task();
        task.setName("Task Name");
        task.setTaskId(1);
        task.setTags("Tag1,Tag2");

        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

        Task createdTask = taskService.createTask(new Task());

        Assertions.assertNotNull(createdTask);
        Assertions.assertEquals("Task Name", createdTask.getName());
        Assertions.assertEquals(1, createdTask.getTaskId());
        Assertions.assertEquals("Tag1,Tag2", createdTask.getTags());
    }


    @Test
    void getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task();
        task1.setName("Task 1");
        task1.setTaskId(1);
        task1.setTags("Tag1");
        tasks.add(task1);

        Task task2 = new Task();
        task2.setName("Task 2");
        task2.setTaskId(2);
        task2.setTags("Tag2");
        tasks.add(task2);

        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> retrievedTasks = taskService.getAllTasks();

        Assertions.assertNotNull(retrievedTasks);
        Assertions.assertEquals(tasks.size(), retrievedTasks.size());
        Assertions.assertEquals("Task 1", retrievedTasks.get(0).getName());
        Assertions.assertEquals(1, retrievedTasks.get(0).getTaskId());
        Assertions.assertEquals("Tag1", retrievedTasks.get(0).getTags());
        Assertions.assertEquals("Task 2", retrievedTasks.get(1).getName());
        Assertions.assertEquals(2, retrievedTasks.get(1).getTaskId());
        Assertions.assertEquals("Tag2", retrievedTasks.get(1).getTags());
    }

    @Test
    void getTaskById() {
            Task existingTask = new Task();
            existingTask.setName("Task 1");
            existingTask.setTaskId(1);
            existingTask.setTags("Tag1");

            Mockito.when(taskRepository.findById(1)).thenReturn(Optional.of(existingTask));

            Task retrievedTask = taskService.getTaskById(1);

            Assertions.assertNotNull(retrievedTask);
            Assertions.assertEquals("Task 1", retrievedTask.getName());
            Assertions.assertEquals(1, retrievedTask.getTaskId());
            Assertions.assertEquals("Tag1", retrievedTask.getTags());

            Mockito.when(taskRepository.findById(2)).thenReturn(Optional.empty());

            Assertions.assertThrows(NullPointerException.class, () -> {
                taskService.getTaskById(2);
            });
    }

    @Test
    void updateTask() {
        Task existingTask = new Task();
        existingTask.setTaskId(1);
        existingTask.setName("Existing Task");
        existingTask.setTags("Tag1");

        Mockito.when(taskRepository.findById(1)).thenReturn(Optional.of(existingTask));

        String updatedName = "Updated Task";
        String updatedTags = "Tag1,Tag2";

        existingTask.setTags(updatedTags);
        existingTask.setName(updatedName);
        taskService.updateTask(existingTask);

        Assertions.assertEquals(updatedName, existingTask.getName());
        Assertions.assertEquals(updatedTags, existingTask.getTags());

        Mockito.verify(taskRepository, Mockito.times(1)).save(existingTask);
    }

}