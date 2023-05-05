package com.example.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId")
    private int taskId;

    @Column(name = "name")
    private String name;

    @Column(name = "tags")
    private String tags;

    @OneToOne(mappedBy = "task")
    private TimeRegistration registration;

}