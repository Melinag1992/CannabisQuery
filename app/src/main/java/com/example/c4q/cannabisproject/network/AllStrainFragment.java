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

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllStrainFragment extends Fragment {
    private View view;

    public AllStrainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_strain, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        setRetrofit(recyclerView);


        return view;

    }

    public void setRetrofit(RecyclerView recyclerView) {
        final RecyclerView recView = recyclerView;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://strainapi.evanbusse.com/6oJPfWj/")
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
