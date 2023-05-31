package com.example.backend.service;

import com.example.backend.model.Project;
import com.example.backend.repository.ProjectRepository;
import com.example.backend.repository.TimeRegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectServiceTest {
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private TimeRegistrationRepository timeRegistrationRepository;

    private ProjectService projectService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        projectService = new ProjectService(projectRepository, timeRegistrationRepository);
    }
    @Test
    public void createProject_HappyPath(){
        // Arrange
        Project project = new Project();
        Mockito.when(projectRepository.save(project)).thenReturn(project);

        // Act
        Project createdProject = projectService.createProject(project);

        // Assert
        Assertions.assertEquals(project, createdProject);
        Mockito.verify(projectRepository, Mockito.times(1)).save(project);
    }
    @Test
    public void createProject_EdgeCase() {
        // Arrange
        Project project = null;

        // Act & Assert
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> {
            projectService.createProject(project);
        });

        // Assert
        Assertions.assertEquals("Project is null", exception.getMessage());
        Mockito.verify(projectRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void getProjectById() {
        Project project = new Project();
        // Arrange
        int projectId = 1;
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // Act
        Project result = projectService.getProjectById(projectId);

        // Assert
        Assertions.assertEquals(project, result);
        Mockito.verify(projectRepository, Mockito.times(1)).findById(projectId);
    }
    @Test
    public void getProjectById_ThrowsException() {
        // Arrange
        int projectId = 1;
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            projectService.getProjectById(projectId);
        });
        Mockito.verify(projectRepository, Mockito.times(1)).findById(projectId);
    }

    @Test
    void getAllProjects_HappyPath() {
        List<Project> expectedProjects = new ArrayList<>();
        expectedProjects.add(new Project(1, "Project 1"));
        expectedProjects.add(new Project(2, "Project 2"));

        Mockito.when(projectRepository.findAll()).thenReturn(expectedProjects);

        // Act
        List<Project> actualProjects = projectService.getAllProjects();

        // Assert
        Assertions.assertEquals(expectedProjects, actualProjects);
        Mockito.verify(projectRepository, Mockito.times(1)).findAll();
    }


    @Test
    public void updateProject_EdgeCase() {
        // Arrange
        Project project = null;

        // Act & Assert
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> {
            projectService.updateProject(project);
        });

        // Assert
        Assertions.assertEquals("Project not updated, it is null", exception.getMessage());
        Mockito.verify(projectRepository, Mockito.never()).save(Mockito.any());
    }

}