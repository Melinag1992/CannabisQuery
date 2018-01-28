package com.example.c4q.cannabisproject.network;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.cannabisproject.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class StrainNameFragment extends Fragment {
    private View view;
    Strain strain;
    List<String> strainNameList = new ArrayList<>();
    List<Strain> strainObjects = new ArrayList<>();
    protected HashMap<String, Strain> strainName;
    private RecyclerView recyclerView;

    public StrainNameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_strain_name, container, false);
//
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        setRetrofit(recyclerView);


        return view;
    }

    public void setRetrofit(RecyclerView recycView) {
        this.recyclerView = recycView;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://strainapi.evanbusse.com/6oJPfWj/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        StrainAPI strainBaseService = retrofit.create(StrainAPI.class);
        Call<HashMap<String,Strain>> getAllStrain = strainBaseService.getAllStrain();
        getAllStrain.enqueue(new Callback<HashMap<String,Strain>>() {
            @Override
            public void onResponse(Call<HashMap<String,Strain>> call, Response<HashMap<String,Strain>> response) {

                strainName = response.body();


                for(Map.Entry<String, Strain> entry: strainName.entrySet()) {
                    strainNameList.add(entry.getKey());
                    strainObjects.add(entry.getValue());
                }

//                Log.d(TAG, "onResponse: " + strainNameList.toString());
                StrainAdapter adapter = new StrainAdapter(strainObjects);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<HashMap<String,Strain>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });

    }

}


