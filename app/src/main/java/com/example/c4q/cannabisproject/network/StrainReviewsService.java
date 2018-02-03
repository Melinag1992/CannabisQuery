package com.example.c4q.cannabisproject.network;

import com.example.c4q.cannabisproject.model.ListOfStrains;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by amirahoxendine on 2/1/18.
 */

public interface StrainReviewsService {
    @GET("/{ocpc}/reviews")
    Call<ListOfStrains> getdata(@Path("ocpc") String ocpc);
}
