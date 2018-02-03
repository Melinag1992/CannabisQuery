package com.example.c4q.cannabisproject;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.c4q.cannabisproject.controller.DetailAdapter;
import com.example.c4q.cannabisproject.model.ListOfStrains;
import com.example.c4q.cannabisproject.model.CannabisStrain;
import com.example.c4q.cannabisproject.model.Meta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by melg on 1/28/18.
 */

public class StrainGrid extends AppCompatActivity {


    //    private List<String> strainNameList = new ArrayList<>();
    List<CannabisStrain> strainObjects = new ArrayList<>();
    Meta metaObjects;
    private String name;
    private String url;
    private String image;
    private String ucpc;

    private int current_page;
    private int total_page;
    private String sortby;

    RecyclerView recyclerView;
    DetailAdapter detailAdapter;

    //    private HashMap<String, Strain> strainName;
//    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_strain);

        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        detailAdapter = new DetailAdapter(strainObjects, getApplicationContext());
        recyclerView.setAdapter(detailAdapter);


        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {


                int lastVisisibleItem = ((GridLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();


                if (lastVisisibleItem == strainObjects.size() - 1) {



                    if (current_page + 1 <= total_page) {
                        Call<ListOfStrains> getpage = CustomRetroFitService.getInstance()
                                .getApi()
                                .getpage(50, sortby, ++current_page);
                        getpage.enqueue(new Callback<ListOfStrains>() {
                            @Override
                            public void onResponse(Call<ListOfStrains> call, Response<ListOfStrains> response) {
                                setUI(response, recyclerView);
                                Log.d(TAG, "onResponse: " + " get page success");
                            }


                            @Override
                            public void onFailure(Call<ListOfStrains> call, Throwable t) {
                                Log.d(TAG, "onFailure: " + "page failure");

                            }
                        });



                    }
                }


            }


        });
        setRetrofit(recyclerView);
    }

    public void setRetrofit(final RecyclerView recycView) {
        this.recyclerView = recycView;

        Call<ListOfStrains> getData = CustomRetroFitService.getInstance()
                .getApi()
                .getdata(50, sortby);

        getData.enqueue(new Callback<ListOfStrains>() {
            @Override
            public void onResponse(Call<ListOfStrains> call, Response<ListOfStrains> response) {
                setUI(response, recycView);
            }

            @Override
            public void onFailure(Call<ListOfStrains> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }
        });

    }

    private void setUI(Response<ListOfStrains> response, RecyclerView recycView) {
        if (response.isSuccessful()) {
            strainObjects.addAll(response.body().getData());
            metaObjects = response.body().getMeta();
            current_page = response.body().getMeta().getPagination().getCurrent_page();
            total_page = response.body().getMeta().getPagination().getTotal_pages();

            detailAdapter.notifyDataSetChanged();

            Log.d(TAG, "onResponse: " + name + " " + url);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.name:
                sortby = "name";
                Call<ListOfStrains> getName = CustomRetroFitService.getInstance()
                        .getApi()
                        .getpage(50, sortby, ++current_page);
                getName.enqueue(new Callback<ListOfStrains>() {
                    @Override
                    public void onResponse(Call<ListOfStrains> call, Response<ListOfStrains> response) {
                        setUI(response, recyclerView);
                        Log.d(TAG, "onResponse: " + " get page success");
                    }

                    @Override
                    public void onFailure(Call<ListOfStrains> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + "page failure");

                    }
                });
                return true;

            case R.id.negative_name:
                sortby = "-name";
                Call<ListOfStrains> getNegativeName = CustomRetroFitService.getInstance()
                        .getApi()
                        .getpage(50, sortby, ++current_page);
                getNegativeName.enqueue(new Callback<ListOfStrains>() {
                    @Override
                    public void onResponse(Call<ListOfStrains> call, Response<ListOfStrains> response) {
                        setUI(response, recyclerView);
                        Log.d(TAG, "onResponse: " + " get page success");
                    }

                    @Override
                    public void onFailure(Call<ListOfStrains> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + "page failure");

                    }
                });
                return true;

            case R.id.new_added:
                sortby = "-createdAt";
                Call<ListOfStrains> getNewlyAdded = CustomRetroFitService.getInstance()
                        .getApi()
                        .getpage(50, sortby, ++current_page);
                getNewlyAdded.enqueue(new Callback<ListOfStrains>() {
                    @Override
                    public void onResponse(Call<ListOfStrains> call, Response<ListOfStrains> response) {
                        setUI(response, recyclerView);
                        Log.d(TAG, "onResponse: " + " get page success");
                    }

                    @Override
                    public void onFailure(Call<ListOfStrains> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + "page failure");

                    }
                });
                return true;

            case R.id.old_added:
                sortby = "createdAt";
                Call<ListOfStrains> getOldestAdded = CustomRetroFitService.getInstance()
                        .getApi()
                        .getpage(50, sortby, ++current_page);
                getOldestAdded.enqueue(new Callback<ListOfStrains>() {
                    @Override
                    public void onResponse(Call<ListOfStrains> call, Response<ListOfStrains> response) {
                        setUI(response, recyclerView);
                        Log.d(TAG, "onResponse: " + " get page success");
                    }

                    @Override
                    public void onFailure(Call<ListOfStrains> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + "page failure");

                    }
                });
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ;


}


