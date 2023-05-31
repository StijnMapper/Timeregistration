package com.example.backend.service;

import com.example.backend.model.*;
import com.example.backend.repository.ProjectRepository;
import com.example.backend.repository.TimeRegistrationRepository;
import com.example.backend.repository.TimerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TimeRegistrationServiceTest {
    @Mock
    private TimeRegistrationRepository timeRegistrationRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private TimerRepository timerRepository;
    private TimeRegistrationService registrationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        registrationService = new TimeRegistrationService(timeRegistrationRepository, projectRepository, timerRepository);
    }

    @Test
    void getTimeRegistrationsByProjectId_HappyPath() {

        // Arrange
        int projectId = 1;
        List<TimeRegistration> expectedRegistrations = Arrays.asList(new TimeRegistration(1, new Project(), null, new Timer(), new Task()), new TimeRegistration(2, new Project(), new User(), new Timer(), new Task()));
        when(timeRegistrationRepository.findByProjectProjectId(projectId)).thenReturn(expectedRegistrations);

        // Act
        List<TimeRegistration> actualRegistrations = registrationService.getTimeRegistrationsByProjectId(projectId);

        // Assert
        assertEquals(expectedRegistrations, actualRegistrations);
        Mockito.verify(timeRegistrationRepository, Mockito.times(1)).findByProjectProjectId(projectId);
    }

    @Test
    void getTimeRegistrationsByProjectId_EdgePath() {
        // Arrange
        int projectId = 1;
        List<TimeRegistration> expectedRegistrations = new ArrayList<>(); // lege lijst
        when(timeRegistrationRepository.findByProjectProjectId(projectId)).thenReturn(expectedRegistrations);

        // Act
        List<TimeRegistration> actualRegistrations = registrationService.getTimeRegistrationsByProjectId(projectId);

        // Assert
        assertEquals(expectedRegistrations, actualRegistrations);
        Mockito.verify(timeRegistrationRepository, Mockito.times(1)).findByProjectProjectId(projectId);
    }


    @Test
    void getAllTimeRegistrations_HappyPath() {

        // Arrange
        List<TimeRegistration> expectedRegistrations = new ArrayList<>(); // Vul de lijst met verwachte tijdregistraties
        when(timeRegistrationRepository.findAll()).thenReturn(expectedRegistrations);

        // Act
        List<TimeRegistration> actualRegistrations = registrationService.getAllTimeRegistrations();

        // Assert
        assertEquals(expectedRegistrations, actualRegistrations);
        Mockito.verify(timeRegistrationRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getAllTimeRegistrations_EmptyList() {
        // Arrange
        List<TimeRegistration> expectedRegistrations = new ArrayList<>(); // Lege lijst verwacht
        when(timeRegistrationRepository.findAll()).thenReturn(expectedRegistrations);

        // Act
        List<TimeRegistration> actualRegistrations = registrationService.getAllTimeRegistrations();

        // Assert
        assertEquals(expectedRegistrations, actualRegistrations);
        Mockito.verify(timeRegistrationRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getTimeRegistrationById_HappyPath() {
        // Arrange
        int timeRegistrationId = 1;
        TimeRegistration expectedTimeRegistration = new TimeRegistration(); // Vervang dit door een echte instantie van TimeRegistration met de gewenste gegevens
        when(timeRegistrationRepository.findById(timeRegistrationId)).thenReturn(Optional.of(expectedTimeRegistration));

        // Act
        TimeRegistration actualTimeRegistration = registrationService.getTimeRegistrationById(timeRegistrationId);

        // Assert
        assertEquals(expectedTimeRegistration, actualTimeRegistration);
        Mockito.verify(timeRegistrationRepository, Mockito.times(1)).findById(timeRegistrationId);
    }
    @Test
    public void getTimeRegistrationById_NonExistingId() {
        // Arrange
        int timeRegistrationId = 100;
        when(timeRegistrationRepository.findById(timeRegistrationId)).thenReturn(Optional.empty());

        // Act
        TimeRegistration actualTimeRegistration = registrationService.getTimeRegistrationById(timeRegistrationId);

        // Assert
        assertNull(actualTimeRegistration);
        Mockito.verify(timeRegistrationRepository, Mockito.times(1)).findById(timeRegistrationId);
    }

    @Test
    void createTimeRegistration_HappyPath(){
        // Arrange
        Project project = new Project(10,"project10",null,"description10",null,null,5.0,false,null,null);
        Timer timer = new Timer(3,LocalDateTime.now(),LocalDateTime.now(),0);
        TimeRegistration timeRegistration = new TimeRegistration();
        timeRegistration.setRegistrationId(1);
        timeRegistration.setProject(project);
        timeRegistration.setUser(null);
        timeRegistration.setTimer(timer);
        timeRegistration.setTask(null);

        when(timeRegistrationRepository.save(Mockito.any(TimeRegistration.class))).thenReturn(timeRegistration);

        // Act
        TimeRegistration result = registrationService.createTimeRegistration(timeRegistration);

        // Assert
        assertNotNull(result);
    }
    @Test
    public void createTimeRegistration_EdgePath() {
        TimeRegistration timeRegistration = null;

        // Act
        TimeRegistration result = registrationService.createTimeRegistration(timeRegistration);

        // Assert
        assertNull(result);
    }


}