package com.example.c4q.cannabisproject.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c4q.cannabisproject.R;
import com.example.c4q.cannabisproject.model.Data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melg on 1/28/18.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailScreenViewHolder> {

    private List<Data> strainObject = new ArrayList<>();

    private View view;
    private Context context;

    public DetailAdapter(List<Data> strains, Context context) {
        this.strainObject = strains;
        this.context = context;

    }

    @Override
    public DetailScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.strain_iv, parent, false);
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
        private TextView name;
        private ImageView imageV;
        private TextView url;

        public DetailScreenViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            imageV = itemView.findViewById(R.id.image_view);

        }



        public void onBind(Data strain) {

            name.setText(strain.getName());

           Picasso.with(context)
                   .load(strain.getImage())
                   .resize(400,400)
                   .centerCrop()
                   .into(imageV);
        }
    }
}
