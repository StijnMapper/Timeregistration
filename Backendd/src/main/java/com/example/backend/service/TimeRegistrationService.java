package com.example.backend.service;

import com.example.backend.model.TimeRegistration;
import com.example.backend.repository.ProjectRepository;
import com.example.backend.repository.TimeRegistrationRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@Data
@AllArgsConstructor
public class TimeRegistrationService {
    private final TimeRegistrationRepository timeRegistrationRepository;
    private final ProjectRepository projectRepository;

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
    // TimeRegistrationService


    public TimeRegistration updateTimeRegistration(int timeRegistrationId, TimeRegistration timeRegistrationDetails) {
            TimeRegistration timeRegistration = timeRegistrationRepository.findById(timeRegistrationId).orElse(null);

                return timeRegistrationRepository.save(timeRegistration);
        }



    public boolean deleteTimeRegistration(int timeRegistrationId) {
        TimeRegistration timeRegistration = timeRegistrationRepository.findById(timeRegistrationId).orElse(null);
        if (timeRegistration != null) {
            timeRegistrationRepository.delete(timeRegistration);
            return true;
        } else {
            return false;
        }
    }

}
