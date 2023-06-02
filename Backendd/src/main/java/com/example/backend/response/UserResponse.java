package com.example.backend.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String type;
    private List<ProjectResponse> projects;
    private List<TimeRegistrationResponse> registrations;

}
