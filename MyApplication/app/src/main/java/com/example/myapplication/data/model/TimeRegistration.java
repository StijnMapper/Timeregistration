package com.example.myapplication.data.model;

public class TimeRegistration {
        private int registrationId;

        private Project project;
        private User user;

        private Timer timer;

        private Task task;

        public int getRegistrationId() {
                return registrationId;
        }

        public void setRegistrationId(int registrationId) {
                this.registrationId = registrationId;
        }

        public Project getProject() {
                return project;
        }

        public void setProject(Project project) {
                this.project = project;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public Timer getTimer() {
                return timer;
        }

        public void setTimer(Timer timer) {
                this.timer = timer;
        }

        public Task getTask() {
                return task;
        }

        public void setTask(Task task) {
                this.task = task;
        }
}




