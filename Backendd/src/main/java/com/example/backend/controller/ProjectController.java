package com.example.backend.controller;

import com.example.backend.model.Project;
import com.example.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects/{id}")
    public Project getProjectById(@PathVariable int id) {
        Optional<Project> optionalProject = Optional.ofNullable(projectService.getProjectById(id));
        return optionalProject.orElseThrow(() -> new NullPointerException("Project not found with id: " + id));
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping("/projects/create")
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") int projectId, @RequestBody Project project) {
        Optional<Project> existingProject = Optional.ofNullable(projectService.getProjectById(projectId));
        if (existingProject.isPresent()) {
            project.setProjectId(projectId);
            Project updatedProject = projectService.updateProject(project);
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("projects/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Product with id " + id + " is deleted successfully.");
    }
}




