package com.example.c4q.cannabisproject.network;

import com.example.c4q.cannabisproject.model.ListOfStrains;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by melg on 1/28/18.
 */

public interface OrbetaAPI {

    @GET("strains?count=50")
    Call<ListOfStrains> getdata();
}
