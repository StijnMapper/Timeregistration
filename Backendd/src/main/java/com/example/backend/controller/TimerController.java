package com.example.backend.controller;

import com.example.backend.model.Timer;
import com.example.backend.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/timer")
public class TimerController {

    @Autowired
    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Timer>> getAllTimers() {
        List<Timer> timers = timerService.getAllTimers();
        return new ResponseEntity<>(timers, HttpStatus.OK);
    }

    @GetMapping("get/{timerId}")
    public ResponseEntity<Timer> getTimerById(@PathVariable("timerId") int timerId) {
        Timer timer = timerService.getTimerById(timerId);
        return new ResponseEntity<>(timer, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Timer> createTimer(@Valid @RequestBody Timer timer) {
        Timer createdTimer = timerService.createTimer(timer);
        return new ResponseEntity<>(createdTimer, HttpStatus.CREATED);
    }

    @PutMapping("update/{timerId}")
    public ResponseEntity<Timer> updateTimer(@PathVariable("timerId") int timerId, @Valid @RequestBody Timer timer) {
        timer.setTimerId(timerId);
        Timer updatedTimer = timerService.updateTimer(timer);
        return new ResponseEntity<>(updatedTimer, HttpStatus.OK);
    }

    @DeleteMapping("/{timerId}")
    public ResponseEntity<String> deleteTimer(@PathVariable("timerId") int timerId) {
        boolean isDeleted = timerService.deleteTimer(timerId);
        if (isDeleted) {
            return new ResponseEntity<>("Timer with ID: " + timerId + " has been successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Timer with ID: " + timerId + " does not exist", HttpStatus.NOT_FOUND);
        }
    }


}
