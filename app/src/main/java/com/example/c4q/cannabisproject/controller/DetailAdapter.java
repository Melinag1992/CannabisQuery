package com.example.c4q.cannabisproject.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.cannabisproject.R;
import com.example.c4q.cannabisproject.model.Strain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melg on 1/28/18.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailScreenViewHolder> {

    private List<Strain> strainObject = new ArrayList<>();
    private List<String> strainNameLsit = new ArrayList<>();
    private View view;

    private DetailAdapter(List<Strain> strains, List<String> namesList){
        this.strainNameLsit = namesList;
        this.strainObject = strains;

    }

    @Override
    public DetailScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.strain_item_view, parent, false);
        return new DetailScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailScreenViewHolder holder, int position) {
        holder.onBind(strainObject.get(position));

    }

    @Override
    public int getItemCount() {
        return strainObject.size();
    }
    class DetailScreenViewHolder extends RecyclerView.ViewHolder {

        public DetailScreenViewHolder(View itemView) {
            super(itemView);
        }

        public void onBind(Strain strain) {
        }
    }
}
