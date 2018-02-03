package com.example.c4q.cannabisproject;

import com.example.c4q.cannabisproject.network.OrbetaAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by melg on 2/3/18.
 */

public class CustomRetroFitService {


    private static CustomRetroFitService instance;

    private CustomRetroFitService() {
    }

    public static CustomRetroFitService getInstance() {
        if (instance == null) {
            instance = new CustomRetroFitService();
        }
        return instance;
    }


    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.otreeba.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    OrbetaAPI getApi() {
        return getRetrofit().create(OrbetaAPI.class);
    }


}
