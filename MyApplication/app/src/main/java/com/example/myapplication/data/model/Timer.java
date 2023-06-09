package com.example.myapplication.data.model;

import com.google.gson.annotations.SerializedName;

public class Timer {
    private int timerId;
    private String startTime;
    private String endTime;
    private int duration;
    @SerializedName("registration")
    private TimeRegistration timeRegistration;



    public int getTimerId() {
        return timerId;
    }

    public void setTimerId(int timerId) {
        this.timerId = timerId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

