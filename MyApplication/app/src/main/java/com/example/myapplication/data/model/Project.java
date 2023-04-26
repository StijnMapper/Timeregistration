package com.example.myapplication.data.model;

import com.google.gson.annotations.SerializedName;

public class Project {
    private int projectId;

    private String name;
    private String status;
    private String description;
    private String user;
    private String client;
    private double budget;
    private boolean billable;
    private String goal;
    private String finalDeadline;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getFinalDeadline() {
        return finalDeadline;
    }

    public void setFinalDeadline(String finalDeadline) {
        this.finalDeadline = finalDeadline;
    }
}
