package com.example.myapplication.retrofit;

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
            }
            return retrofit;
        }



}






