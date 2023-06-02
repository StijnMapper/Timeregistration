package com.example.myapplication.service;

import com.example.myapplication.data.model.TimeRegistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TimeRegistrationService {

   @GET("registrations/project/{projectId}")
   Call<List<TimeRegistration>> getTimeRegistrationsByProjectId(@Path("projectId") int projectId);


    @DELETE("registrations/project/{projectId}/{registrationId}")
    Call<Void> deleteTimeRegistrationsByProjectId(@Path("projectId") int projectId, @Path("registrationId")int registrationId);

    @GET("all")
    Call <List<TimeRegistration>> getAllTimeRegistrations();



    @PUT("update/registrations/project/{projectId}/{registrationId}")
        Call<TimeRegistration> updateTimeRegistrationProject(
                @Path("projectId") int projectId,
                @Path("registrationId") int registrationId,
                @Body TimeRegistration timeRegistration
        );

    @DELETE("delete/registration/{timeRegistrationId}")
    Call<Void> deleteTimeRegistration(@Path("timeRegistrationId") int registrationId);



    @PUT("update/{timeRegistrationId}")
    Call<TimeRegistration> updateTimeRegistration(@Path("timeRegistrationId") int timeRegistrationId, @Body TimeRegistration timeRegistration);
}
