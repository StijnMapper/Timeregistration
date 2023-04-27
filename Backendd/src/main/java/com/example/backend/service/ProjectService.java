package com.example.backend.service;

import com.example.backend.model.Project;
import com.example.backend.repository.ProjectRepository;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {

        this.projectRepository = projectRepository;
    }

    //OK
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
    public Project getProjectById(int id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        return optionalProject.orElseThrow(() -> new NullPointerException("Project not found with id: " + id));
    }


    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(int projectId) {
        projectRepository.deleteById(projectId);
    }
}
