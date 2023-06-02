package com.example.backend.service;

import com.example.backend.model.Project;
import com.example.backend.model.TimeRegistration;
import com.example.backend.repository.ProjectRepository;
import com.example.backend.repository.TimeRegistrationRepository;
import com.example.backend.repository.TimerRepository;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TimeRegistrationRepository timeRegistrationRepository;
    private TimerRepository timerRepository;

    public ProjectService(ProjectRepository projectRepository, TimeRegistrationRepository timeRegistrationRepository) {

        this.projectRepository = projectRepository;
        this.timeRegistrationRepository = timeRegistrationRepository;
    }


    //OK
    public Project createProject(Project project) {
        if (project == null) {
            throw new NullPointerException("Project is null");
        }
        return projectRepository.save(project);
    }

    public Project getProjectById(int id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        return optionalProject.orElseThrow(() -> new NullPointerException("Project not found with id: " + id));
    }

    @GetMapping("/projects/{projectId}/registrations")
    public ResponseEntity<Optional<TimeRegistration>> getTimeRegistrationsByProjectId(@PathVariable int projectId) {
        Optional<TimeRegistration> timeRegistrations = timeRegistrationRepository.findById(projectId);
        return ResponseEntity.ok().body(timeRegistrations);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project updateProject(Project project) {

        if (project == null) {
            throw new NullPointerException("Project not updated, it is null");
        }
        return projectRepository.save(project);
    }

    public void deleteProject(int projectId) {
        projectRepository.deleteById(projectId);
    }
}
