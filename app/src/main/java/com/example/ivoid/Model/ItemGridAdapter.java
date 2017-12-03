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

import static com.example.ivoid.R.color.lightGray;

/**
 * Created by Justin Dang on 12/3/2017.
 */

public class ItemGridAdapter extends RecyclerView.Adapter<ItemGridAdapter.ItemViewHolder> {
    //championViewHolder
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public TextView itemName;
        public ItemViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.image_view_champion_card);
            itemName = (TextView) itemView.findViewById(R.id.text_view_champion_name_card);
        }
    }
    //member variables
    private Context context;
    private ArrayList<Item> itemList;

    //constructor
    public ItemGridAdapter(Context context, ArrayList<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    //layout inflater
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.champion_grid_card, parent, false);
        return new ItemViewHolder(v);
    }
    //view binder
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(item.getName());
        holder.itemImage.setBackgroundColor(lightGray);
        //image loader with picasso
        Picasso.with(context)
                .load("http://elohell.net/public/champions/avatar/VelKozSquare1.png")
                .into(holder.itemImage);
    }
    @Override
    //get the number of selected toppings
    public int getItemCount() {
        if (itemList == null) {
            return 0;
        }
        return itemList.size();
    }
}
