package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Project {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "projectId")
        private int projectId;

        @Column(name = "name")
        private String name;

        @Column(name = "status")
        @Enumerated(EnumType.STRING)
        private Status status;

        @Column(name = "description")
        private String description;

        @ManyToOne
        @JoinColumn(name = "user")
        private User user;

        // getters and setters


}
