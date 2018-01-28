package com.example.c4q.cannabisproject.network;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.c4q.cannabisproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c4q on 1/23/18.
 */

public class StrainAdapter extends RecyclerView.Adapter<StrainAdapter.StrainViewHolder> {

    private List<Strain> strainList = new ArrayList<>();
    private View view;


    StrainAdapter(List<Strain> strainList){
        this.strainList = strainList;

    }

    @Override
    public StrainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     view = LayoutInflater.from(parent.getContext()).inflate(R.layout.strain_item_view, parent, false);
     return new StrainViewHolder(view);

    }

    @Override
    public void onBindViewHolder(StrainViewHolder holder, int position) {
        holder.onBind(strainList.get(position));


    }

    @Override
    public int getItemCount() {
        return strainList.size();
    }

    class StrainViewHolder extends RecyclerView.ViewHolder {
//        private TextView name;
        private TextView race;

        public StrainViewHolder(View itemView) {
            super(itemView);

            race = itemView.findViewById(R.id.race);
//            name = itemView.findViewById(R.id.name);
        }
        public void onBind(Strain strain){

            race.setText("Type: " + strain.getRace());
//            name.setText(strain.getName());

        }
    }
}
