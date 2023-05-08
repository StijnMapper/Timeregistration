package com.example.backend.service;

import com.example.backend.model.TimeRegistration;
import com.example.backend.repository.ProjectRepository;
import com.example.backend.repository.TimeRegistrationRepository;
import com.example.backend.repository.TimerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Data
@AllArgsConstructor
public class TimeRegistrationService {
    private final TimeRegistrationRepository timeRegistrationRepository;
    private final ProjectRepository projectRepository;
    private final TimerRepository timerRepository;

    public List<TimeRegistration> getTimeRegistrationsByProjectId(int projectId) {
        return timeRegistrationRepository.findByProjectProjectId(projectId);
    }


    public List<TimeRegistration> getAllTimeRegistrations() {
        return timeRegistrationRepository.findAll();
    }

    public TimeRegistration getTimeRegistrationById(int timeRegistrationId) {
        return timeRegistrationRepository.findById(timeRegistrationId).orElse(null);
    }


    public TimeRegistration createTimeRegistration(TimeRegistration timeRegistration) {
        return timeRegistrationRepository.save(timeRegistration);
    }

    public TimeRegistration updateTimeRegistration(int timeRegistrationId, TimeRegistration timeRegistrationDetails) {
        TimeRegistration timeRegistration = timeRegistrationRepository.findById(timeRegistrationId).orElse(null);

        return timeRegistrationRepository.save(timeRegistration);
    }

    public boolean deleteTimeRegistration(int registrationId) {
        if (timeRegistrationRepository.existsById(registrationId)) {
            timeRegistrationRepository.deleteById(registrationId);
            return true;
        } else {
            return false;
        }
    }

}
