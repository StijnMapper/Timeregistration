package com.example.myapplication.service;

import com.example.myapplication.data.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProjectService {
        @GET("projects/{id}")
        Call<Project> getProjectById(@Path("id") int id);

        @GET("projects")
        Call<List<Project>> getAllProjects();

        @POST("projects/create")
        Call<Project> createProject(@Body Project project);



}




