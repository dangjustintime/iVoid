package com.example.ivoid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ivoid.Model.ItemListAdapter;

public class  ItemsActivity extends AppCompatActivity {
    //views
    private RecyclerView itemsRecyclerView;
    private RecyclerView.Adapter itemsRecyclerViewAdapter;
    private RecyclerView.LayoutManager itemsRecyclerViewLayoutManager;
    private CardView itemCardView;
    ItemListAdapter itemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        itemCardView = (CardView) findViewById(R.id.item_card_view);
        itemCardView.setCardBackgroundColor(R.color.lightGray);
    }
    public void itemsInfoClick(View v) {
        //Start ItemsActivity
        Intent intent = new Intent (ItemsActivity.this, ItemInfoActivity.class);
        startActivity(intent);
    }

}
