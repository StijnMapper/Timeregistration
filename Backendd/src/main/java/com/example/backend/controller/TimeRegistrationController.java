package com.example.backend.controller;

import com.example.backend.model.TimeRegistration;
import com.example.backend.response.TaskResponse;
import com.example.backend.response.TimeRegistrationResponse;
import com.example.backend.response.TimerResponse;
import com.example.backend.service.TimeRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TimeRegistrationController {
    @Autowired
    private TimeRegistrationService timeRegistrationService;

    @GetMapping("/registrations/project/{projectId}")
    public ResponseEntity<List<TimeRegistrationResponse>> getTimeRegistrationsByProjectId(@PathVariable int projectId) {
        List<TimeRegistration> timeRegistrations = timeRegistrationService.getTimeRegistrationsByProjectId(projectId);
        if (timeRegistrations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<TimeRegistrationResponse> responseList = timeRegistrations.stream()
                    .map(TimeRegistrationResponse::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<TimeRegistration> createTimeRegistration(@RequestBody TimeRegistration timeRegistration) {
        TimeRegistration createdTimeRegistration = timeRegistrationService.createTimeRegistration(timeRegistration);
        return new ResponseEntity<>(createdTimeRegistration, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TimeRegistration>> getAllTimeRegistrations() {
        List<TimeRegistration> timeRegistrations = timeRegistrationService.getAllTimeRegistrations();
        return new ResponseEntity<>(timeRegistrations, HttpStatus.OK);
    }

    @GetMapping("get/{timeRegistrationId}")
    public ResponseEntity<TimeRegistration> getTimeRegistrationById(@PathVariable int timeRegistrationId) {
        TimeRegistration timeRegistration = timeRegistrationService.getTimeRegistrationById(timeRegistrationId);
        if (timeRegistration == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(timeRegistration, HttpStatus.OK);
        }
    }

    @PutMapping("/update/{timeRegistrationId}")
    public ResponseEntity<TimeRegistration> updateTimeRegistration(@PathVariable int timeRegistrationId, @RequestBody TimeRegistration timeRegistrationDetails) {
        TimeRegistration updatedTimeRegistration = timeRegistrationService.updateTimeRegistration(timeRegistrationId, timeRegistrationDetails);
        if (updatedTimeRegistration == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedTimeRegistration, HttpStatus.OK);
        }
    }
    @DeleteMapping("delete/{timeRegistrationId}")
    public ResponseEntity<String> deleteTimeRegistration(@PathVariable int timeRegistrationId) {
        boolean deleted = timeRegistrationService.deleteTimeRegistration(timeRegistrationId);
        if (deleted) {
            return new ResponseEntity<>("Time registration with id " + timeRegistrationId + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Time registration with id " + timeRegistrationId + " not found.", HttpStatus.NOT_FOUND);
        }
    }}



