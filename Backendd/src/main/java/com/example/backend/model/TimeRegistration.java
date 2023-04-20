package com.example.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeRegistration {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "registrationId")
        private int registrationId;

        @ManyToOne
        @JoinColumn(name = "projectId")
        private Project project;

        @ManyToOne
        @JoinColumn(name = "user")
        private User user;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "timerId")
        private Timer timer;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "taskId")
        private Task task;

    }

