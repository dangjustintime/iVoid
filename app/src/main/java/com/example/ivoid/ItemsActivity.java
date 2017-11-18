package com.example.ivoid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.ivoid.Dto.ItemListDto;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

public class ItemsActivity extends AppCompatActivity {
    //views
    private RecyclerView itemsRecyclerView;
    private RecyclerView.Adapter itemsRecyclerViewAdapter;
    private RecyclerView.LayoutManager itemsRecyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        itemsRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_items);

        itemsRecyclerView.hasFixedSize();
        itemsRecyclerViewLayoutManager = new LinearLayoutManager(this);
        itemsRecyclerView.setLayoutManager(itemsRecyclerViewLayoutManager);
    }
    public void itemsInfoClick(View v) {
        //Start ItemsActivity
        Intent intent = new Intent (ItemsActivity.this, ItemInfoActivity.class);
        startActivity(intent);
    }

}
