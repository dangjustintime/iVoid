package com.example.ivoid.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivoid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.ivoid.R.color.purple;

/**
 * Created by Justin Dang on 11/30/2017.
 */

public class ChampionGridAdapter extends RecyclerView.Adapter<ChampionGridAdapter.ChampionViewHolder> {
    //championViewHolder
    public static class ChampionViewHolder extends RecyclerView.ViewHolder {
        public ImageView championImage;
        public TextView championName;
        public ChampionViewHolder(View itemView) {
            super(itemView);
            championImage = (ImageView) itemView.findViewById(R.id.image_view_champion_card);
            championName = (TextView) itemView.findViewById(R.id.text_view_champion_name_card);
        }
    }
    //member variables
    private Context context;
    private ArrayList<Champion> championList;

    //constructor
    public ChampionGridAdapter(Context context, ArrayList<Champion> championList) {
        this.context = context;
        this.championList = championList;
    }
    //layout inflater
    public ChampionViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.champion_grid_card, parent, false);
        return new ChampionViewHolder(v);
    }
    //view binder
    @SuppressLint("ResourceAsColor")
    public void onBindViewHolder(ChampionViewHolder holder, int position) {
        Champion champion = championList.get(position);
        holder.championName.setText(champion.getName());
        holder.championImage.setBackgroundColor(purple);
        //image loader with picasso
        Picasso.with(context)
                .load("http://elohell.net/public/champions/avatar/VelKozSquare1.png")
                .into(holder.championImage);

    }
    @Override
    //get the number of selected toppings
    public int getItemCount() {
        if (championList == null) {
            return 0;
        }
        return championList.size();
    }
}
