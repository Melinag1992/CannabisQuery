package com.example.c4q.cannabisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.c4q.cannabisproject.model.OuterObject;
import com.example.c4q.cannabisproject.model.Data;
import com.example.c4q.cannabisproject.network.OrbetaAPI;

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

public class StrainDetailScreen extends AppCompatActivity {


//    private List<String> strainNameList = new ArrayList<>();
    private List<Data> strainObjects = new ArrayList<>();
//    private HashMap<String, Strain> strainName;
//    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_strain);

//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
//        recyclerView.setLayoutManager(gridLayoutManager);
        setRetrofit();

    }

    public void setRetrofit() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.otreeba.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        OrbetaAPI orbetaAPI = retrofit.create(OrbetaAPI.class);
        Call<OuterObject> getData = orbetaAPI.getdata();

        getData.enqueue(new Callback<OuterObject>() {
            @Override
            public void onResponse(Call<OuterObject> call, Response<OuterObject> response) {

                if (response.isSuccessful()) {
                    strainObjects.clear();
                    strainObjects = response.body().getData();
                Log.d(TAG, "onResponse: " + strainObjects.toString());
                }



            }

            @Override
            public void onFailure(Call<OuterObject> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });

    }
}
