package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId")
    private int taskId;

    @Column(name = "name")
    private String name;

    @Column(name = "tags")
    private String tags;

    @ManyToOne
    @JoinColumn(name = "registrationId")
    private TimeRegistration registration;

    // getters and setters
}