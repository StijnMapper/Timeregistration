package com.example.myapplication.service;

import com.example.myapplication.data.model.TimeRegistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TimeRegistrationService {
    @GET("all")
    Call<List<TimeRegistration>> getTimeRegistrations();

    @GET("registrations/project/{projectId}")
        Call<List<TimeRegistration>> getTimeRegistrationsByProjectId(@Path("projectId") int projectId);
    }



