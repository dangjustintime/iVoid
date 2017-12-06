package com.example.ivoid.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivoid.ChampionInfoActivity;
import com.example.ivoid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.ivoid.R.color.lightGray;

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
    private String [] imageUrls;
    private String [] splashUrls;
    private String [] splashUrls2;
    //constructor
    public ChampionGridAdapter(Context context, ArrayList<Champion> championList) {
        this.context = context;
        this.championList = championList;
        imageUrls = context.getResources().getStringArray(R.array.champion_icon_urls);
        splashUrls = context.getResources().getStringArray(R.array.champion_splash_urls);
        splashUrls2 = context.getResources().getStringArray(R.array.champion_splash_urls2);
    }
    //layout inflater
    public ChampionViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.champion_grid_card, parent, false);
        return new ChampionViewHolder(v);
    }
    //view binder
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ChampionViewHolder holder, final int position) {
        final Champion champion = championList.get(position);
        holder.championName.setText(champion.getName());
        holder.championImage.setBackgroundColor(lightGray);
        //image loader with picasso
        Picasso.with(context)
                .load(imageUrls[position])
                .into(holder.championImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChampionInfoActivity.class);
                intent.putExtra("championId", champion.getId());
                intent.putExtra("splashUrl1", splashUrls[position]);
                intent.putExtra("splashUrl2", splashUrls2[position]);
                context.startActivity(intent);
            }
        });
    }
    @Override
    //get the number of selected toppings
    public int getItemCount() {
        if (championList == null) { return 0; }
        return championList.size();
    }
}
