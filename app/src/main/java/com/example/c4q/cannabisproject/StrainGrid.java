package com.example.c4q.cannabisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.c4q.cannabisproject.R;
import com.example.c4q.cannabisproject.controller.StrainAdapter;
import com.example.c4q.cannabisproject.model.Strain;
import com.example.c4q.cannabisproject.network.StrainAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by melg on 1/28/18.
 */

public class StrainGrid extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_strain);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        setRetrofit(recyclerView);

    }
    public void setRetrofit(RecyclerView recyclerView) {
        final RecyclerView recView = recyclerView;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("api.otreeba.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StrainAPI strainBaseService = retrofit.create(StrainAPI.class);
        Call<List<Strain>> getStrainList = strainBaseService.getDescripStrain();
        getStrainList.enqueue(new Callback<List<Strain>>() {
            @Override
            public void onResponse(Call<List<Strain>> call, Response<List<Strain>> response) {

                List<Strain> strainList = new ArrayList<>();
                strainList.addAll(response.body());



                StrainAdapter adapter = new StrainAdapter(strainList);
                recView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Strain>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });

    }
}
