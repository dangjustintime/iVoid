package com.example.ivoid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.ivoid.Model.Item;
import com.example.ivoid.Model.ItemGridAdapter;
import com.example.ivoid.Model.ItemListAdapter;
import com.example.ivoid.Model.ItemMap;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  ItemsActivity extends AppCompatActivity {
    //views
    private CardView itemCardView;
    private RecyclerView itemsRecyclerView;
    private ItemGridAdapter itemRecyclerAdapter;
    ItemListAdapter itemListAdapter;
    Retrofit retrofit = null;
    ArrayList<Item> itemArrayList;
    ItemMap itemMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        itemCardView = (CardView) findViewById(R.id.item_card_view);
        itemCardView.setCardBackgroundColor(R.color.lightGray);


        //recycler view
        itemsRecyclerView = (RecyclerView) findViewById(R.id.item_grid_recycler_view);
        itemsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //call API
        /* This line of code forces the ItemsActivity to make API calls, four to be exact. Do not uncomment this
        line unless you want to use our api calls, or you need to test and display the application!
         */
        //getAPIData();

    }
    public void itemsInfoClick(View v) {
        //Start ItemsActivity
        Intent intent = new Intent (ItemsActivity.this, ItemInfoActivity.class);
        startActivity(intent);
    }

    //API call
    public void getAPIData() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://na1.api.riotgames.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ApiClient client = retrofit.create(ApiClient.class);
        //call for championMap
        Call<ItemMap> callItemMap = client.reposForItemMap();
        callItemMap.enqueue(new Callback<ItemMap>() {
            @Override
            public void onResponse(Call<ItemMap> call, Response<ItemMap> response) {
                ItemMap responseItemMap = response.body();
                ArrayList<Item> responseItemArrayList = responseItemMap.getList();
                itemsRecyclerView.setAdapter(new ItemGridAdapter(getApplicationContext(), responseItemArrayList));
                Toast.makeText(ItemsActivity.this, "Champion Response Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ItemMap> call, Throwable t) {
                Toast.makeText(ItemsActivity.this, "Champion Response Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

}
