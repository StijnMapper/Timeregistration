package com.example.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

        @Column(name = "client")
        private String client;

        @Column(name = "budget")
        private double budget;

        @Column(name = "billable")
        private boolean billable;

        @Column(name = "goal")
        private String goal;

        @Column(name = "finalDeadline")
        private Date finalDeadline;

        public Project(int projectId, String name) {
                this.projectId = projectId;
                this.name = name;
        }


}
