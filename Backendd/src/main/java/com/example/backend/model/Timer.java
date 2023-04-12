package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import jakarta.persistence.*;


import java.time.LocalDateTime;

@Data
@Entity
public class Timer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timerId")
    private int timerId;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @Column(name = "duration")
    private int duration;

    // getters and setters
}