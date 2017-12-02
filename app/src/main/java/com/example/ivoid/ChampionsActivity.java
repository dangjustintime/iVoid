package com.example.ivoid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.ivoid.Model.Champion;
import com.example.ivoid.Model.ChampionGridAdapter;
import com.example.ivoid.Model.ChampionMap;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChampionsActivity extends AppCompatActivity {

    private CardView championCardView;
    private RecyclerView championsRecyclerView;
    private ChampionGridAdapter championRecyclerAdapter;
    private EditText championsEditText;
    private ChampionMap championMap;
    private ArrayList<Champion> championArrayList;
    private Retrofit retrofit = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);

        championsEditText = (EditText) findViewById(R.id.edit_text_search_item);
        championCardView = (CardView) findViewById(R.id.champion_card_view);
        championCardView.setCardBackgroundColor(R.color.lightGray);

        //recycler view
        championsRecyclerView = (RecyclerView) findViewById(R.id.champion_grid_recycler_view);
        championsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        getAPIData();
    }
    public void championInfoClick(View v) {
        //Start ItemsActivity
        Intent intent = new Intent (ChampionsActivity.this, ChampionInfoActivity.class);
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
        Call<ChampionMap> callChampionMap = client.reposForChampionMap();
        callChampionMap.enqueue(new Callback<ChampionMap>() {
            @Override
            public void onResponse(Call<ChampionMap> call, Response<ChampionMap> response) {
                ChampionMap responseChampionMap = (ChampionMap) response.body().getChampionMap();
                ArrayList<Champion> responseChampionArrayList = responseChampionMap.getList();
                championsRecyclerView.setAdapter(new ChampionGridAdapter(getApplicationContext(), responseChampionArrayList));
            }
            @Override
            public void onFailure(Call<ChampionMap> call, Throwable t) {
                Toast.makeText(ChampionsActivity.this, "Champion Response Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}



