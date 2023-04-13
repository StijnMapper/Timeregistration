package com.example.backend.service;

import com.example.backend.repository.TimerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class TimerService {
    private final TimerRepository timerRepository;
}
