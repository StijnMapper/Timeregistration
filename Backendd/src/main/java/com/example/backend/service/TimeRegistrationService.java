package com.example.backend.service;

import com.example.backend.repository.TimeRegistrationRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class TimeRegistrationService {
    private final TimeRegistrationRepository timeRegistrationRepository;

}
