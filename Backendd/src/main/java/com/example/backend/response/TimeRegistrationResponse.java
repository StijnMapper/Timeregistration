package com.example.backend.response;

import com.example.backend.model.*;
import lombok.*;

@Getter
@Setter
public class TimeRegistrationResponse {
        private int registrationId;
        private TimerResponse timer;
        private TaskResponse task;
        private ProjectResponse project;

        public TimeRegistrationResponse(TimeRegistration timeRegistration) {
            this.registrationId = timeRegistration.getRegistrationId();
            this.timer = new TimerResponse(timeRegistration.getTimer());
            this.task = new TaskResponse(timeRegistration.getTask());
            this.project = new ProjectResponse(timeRegistration.getProject().getProjectId(), timeRegistration.getProject().getName());
        }

}




