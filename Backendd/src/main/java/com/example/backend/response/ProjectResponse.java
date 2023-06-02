package com.example.backend.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectResponse {

    private int projectId;
    private String name;
    private String status;
    private String description;
    private UserResponse user;
    private List<TimeRegistrationResponse> registrations;

    public ProjectResponse(int projectId, String name) {
        this.projectId = projectId;
        this.name = name;
    }
}
