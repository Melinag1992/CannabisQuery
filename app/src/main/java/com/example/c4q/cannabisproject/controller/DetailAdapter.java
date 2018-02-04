package com.example.c4q.cannabisproject.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c4q.cannabisproject.R;
import com.example.c4q.cannabisproject.database.CannabisDBHelper;
import com.example.c4q.cannabisproject.model.CannabisStrain;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.c4q.cannabisproject.database.CannabisDBContract.CannabisEntry._STATUS_FAV;
import static com.example.c4q.cannabisproject.database.CannabisDBContract.CannabisEntry._STATUS_WISH;

/**
 * Created by melg on 1/28/18.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailScreenViewHolder> {


    private List<CannabisStrain> strainObject = new ArrayList<>();
    private View view;
    private Context context;




    public DetailAdapter(List<CannabisStrain> strains, Context context) {
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
        private Button favButton;
        private Button wishButton;

        public DetailScreenViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            imageV = itemView.findViewById(R.id.image_view);
            favButton = itemView.findViewById(R.id.favorite_strain_button);
            wishButton = itemView.findViewById(R.id.wish_strain_button);
        }


        public void onBind(final CannabisStrain strain) {

            name.setText(strain.getName());

            Picasso.with(context)
                    .load(strain.getImage())
                    .resize(550, 550)
                    .centerCrop()
                    .into(imageV);
            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CannabisDBHelper dbHelper = new CannabisDBHelper(context);
                    dbHelper.insertStrain(strain, _STATUS_FAV);
                    dbHelper.close();
                }
            });

            wishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CannabisDBHelper dbHelper = new CannabisDBHelper(context);
                    dbHelper.insertStrain(strain, _STATUS_WISH);
                    dbHelper.close();
                }
            });
        }
    }
}
