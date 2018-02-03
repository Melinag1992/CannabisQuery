package com.example.c4q.cannabisproject.network;

import com.example.c4q.cannabisproject.model.ListOfStrains;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by melg on 1/28/18.
 */

public interface OrbetaAPI {

    @GET("strains?count=50&sort=name")
    Call<ListOfStrains> getdata(@Query("count") int count,
                                @Query("sort") String sortby);

    @GET("strains")
    Call<ListOfStrains> getpage(
            @Query("count") int count,
            @Query("sort") String sortby,
            @Query("page") int page);

}
