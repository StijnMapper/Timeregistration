package com.example.myapplication.retrofit;

import static android.content.ContentValues.TAG;

import android.util.Log;


import com.example.myapplication.data.model.Project;
import com.example.myapplication.service.ProjectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
        private static Retrofit retrofit;
        private static final String BASE_URL = "http://10.0.2.2:8080/api/";

        //Initialize -  maakt een Retrofit-object aan met de basis-URL van de API
        public static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                // Create ProjectService instance
                ProjectService service = retrofit.create(ProjectService.class);
                // Call the getAllProjects() API endpoint asynchronously
                Call<List<Project>> call = service.getAllProjects();
                call.enqueue(new Callback<List<Project>>() {
                    @Override
                    public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                        if (response.isSuccessful()) {
                            List<Project> projects = response.body();
                            // Log successful response
                            Log.d("Tag", "success");

                        } else {
                           // Log Error response
                            Log.d("Tag", "Error");

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Project>> call, Throwable t) {
                        // Log failure response
                        Log.d("Projects", "Faillure: " + t.getMessage());
                    }
                });


            }
            return retrofit;
        }



}






