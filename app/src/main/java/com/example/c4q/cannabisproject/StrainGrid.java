package com.example.c4q.cannabisproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.c4q.cannabisproject.controller.DetailAdapter;
import com.example.c4q.cannabisproject.model.ListOfStrains;
import com.example.c4q.cannabisproject.model.Data;
import com.example.c4q.cannabisproject.model.Meta;
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

public class StrainGrid extends AppCompatActivity {


//    private List<String> strainNameList = new ArrayList<>();
    List<Data> strainObjects = new ArrayList<>();
    Meta metaObjects;
    private String name;
    private String url;
    private String image;
    RecyclerView recyclerView;
//    private HashMap<String, Strain> strainName;
//    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_strain);

        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        setRetrofit(recyclerView);

    }

    public void setRetrofit(final RecyclerView recycView) {
        this.recyclerView = recycView;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.otreeba.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        OrbetaAPI orbetaAPI = retrofit.create(OrbetaAPI.class);
        Call<ListOfStrains> getData = orbetaAPI.getdata();

        getData.enqueue(new Callback<ListOfStrains>() {
            @Override
            public void onResponse(Call<ListOfStrains> call, Response<ListOfStrains> response) {

                if (response.isSuccessful()) {
                    strainObjects.clear();
                    strainObjects = response.body().getData();
                    response.body().getMeta();
                    metaObjects = response.body().getMeta();




                    for (int i = 0; i < strainObjects.size(); i++) {
                       name = strainObjects.get(i).getName();
                       image = strainObjects.get(i).getImage();
                       url = strainObjects.get(i).getUrl();
                    }

                Log.d(TAG, "onResponse: " + name + " " + url );

                    DetailAdapter detailAdapter = new DetailAdapter(strainObjects,getApplicationContext());
                    recycView.setAdapter(detailAdapter);
                }

            }

            @Override
            public void onFailure(Call<ListOfStrains> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });

    }
}
