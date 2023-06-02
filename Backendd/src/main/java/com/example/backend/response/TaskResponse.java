package com.example.backend.response;

import com.example.backend.model.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TaskResponse {
    private String name;
    private String tags;

    public TaskResponse(String name, String tags) {
        this.name = name;
        this.tags = tags;
    }

    public TaskResponse(Task task) {
        if (task != null) {
            this.name = task.getName();
            this.tags = task.getTags();
        }
    }
}