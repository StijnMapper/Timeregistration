package com.example.backend.service;

import com.example.backend.model.TimeRegistration;
import com.example.backend.model.Timer;
import com.example.backend.repository.TimerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class TimerService {
    private final TimerRepository timerRepository;
    private TimeRegistrationService timeRegistrationService;


    public Timer createTimer(Timer timer) {
        return timerRepository.save(timer);
    }

    public Timer updateTimer(Timer timer) {
        return timerRepository.save(timer);
    }

    public boolean deleteTimer(int timerId) {
        timerRepository.deleteById(timerId);
        return false;
    }


    public List<Timer> getAllTimers() {
        return timerRepository.findAll();
    }

    public Timer getTimerById(int timerId) {
        Optional<Timer> timer = timerRepository.findById(timerId);
        return timer.orElseThrow(() -> new NullPointerException("Timer not found"));
    }


}
