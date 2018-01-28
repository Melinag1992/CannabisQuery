package com.example.c4q.cannabisproject.network;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by c4q on 1/23/18.
 */

public interface StrainAPI {
    @GET("strains/search/name/all")
    Call<List<Strain>> getDescripStrain();


    @GET("strains/search/all")
    Call<HashMap<String,Strain>> getAllStrain();

}
