package com.example.backend.controller;

import com.example.backend.model.Project;
import com.example.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/create") //OK
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    //OK
    @GetMapping("/projects/{id}")
    public Project getProjectById(@PathVariable int id) {
        Optional<Project> optionalProject = Optional.ofNullable(projectService.getProjectById(id));
        return optionalProject.orElseThrow(() -> new NullPointerException("Project not found with id: " + id));
    }

    //OK
    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PutMapping("/projects/{id}")
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

    //OK
    @DeleteMapping("projects/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Product with id " + id + " is deleted successfully.");
    }
}




