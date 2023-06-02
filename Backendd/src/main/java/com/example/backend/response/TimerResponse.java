package com.example.backend.response;

import com.example.backend.model.Timer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimerResponse {
    private int timerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;

    public TimerResponse(int timerId, LocalDateTime startTime, LocalDateTime endTime, int duration) {
        this.timerId = timerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public TimerResponse(Timer timer) {
        this.timerId = timer.getTimerId();
        this.startTime = timer.getStartTime();
        this.endTime = timer.getEndTime();
        this.duration = timer.getDuration();
    }

}


