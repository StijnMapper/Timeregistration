package com.example.myapplication.data.model;

import com.google.gson.annotations.SerializedName;

public class Task {
        @SerializedName("taskId")
        private int taskId;

        @SerializedName("name")
        private String name;

        @SerializedName("tags")
        private String tags;

        @SerializedName("registration")
        private TimeRegistration registration;

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public TimeRegistration getRegistration() {
            return registration;
        }

        public void setRegistration(TimeRegistration registration) {
            this.registration = registration;
        }
    }


