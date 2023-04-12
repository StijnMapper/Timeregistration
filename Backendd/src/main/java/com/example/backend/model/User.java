package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToMany(mappedBy = "user")
    private List<Project> projects;

    @OneToMany(mappedBy = "user")
    private List<TimeRegistration> registrations;

    // getters and setters
}