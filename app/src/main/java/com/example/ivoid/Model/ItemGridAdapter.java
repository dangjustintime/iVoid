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

import com.example.ivoid.ItemInfoActivity;
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
    private String[] iconurls;

    //constructor
    public ItemGridAdapter(Context context, ArrayList<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
        iconurls = context.getResources().getStringArray(R.array.item_icon_urls);
    }
    //layout inflater
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.champion_grid_card, parent, false);
        return new ItemViewHolder(v);
    }
    //view binder
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ItemViewHolder holder,final int position) {
        final Item item = itemList.get(position);
        holder.itemName.setText(item.getName());
        holder.itemImage.setBackgroundColor(lightGray);
        //image loader with picasso
        Picasso.with(context)
                .load(iconurls[position])
                .into(holder.itemImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ItemInfoActivity.class);
                intent.putExtra("itemId", item.getId());
                intent.putExtra("iconUrl", iconurls[position]);
                context.startActivity(intent);
            }
        });
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
