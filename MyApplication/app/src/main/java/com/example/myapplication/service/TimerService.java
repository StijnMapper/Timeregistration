package com.example.myapplication.service;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface TimerService {
    @DELETE("delete/{timerId}")
    Call<Void> deleteTimer(@Path("timerId") int timerId);
}
