package com.example.ivoid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ivoid.Model.Champion;
import com.example.ivoid.Model.ChampionGridAdapter;
import com.example.ivoid.Model.ChampionMap;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChampionsActivity extends AppCompatActivity {
    public static final String API_KEY = "RGAPI-3a0b7441-0440-42e9-9546-b0c91bc987fb";

    private RecyclerView championsRecyclerView;
    private Retrofit retrofit = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);

        //bind and regenerate recycler view
        championsRecyclerView = (RecyclerView) findViewById(R.id.champion_grid_recycler_view);
        championsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //call API
        /* This line of code forces the ChampionsActivity to make API calls, four to be exact. Do not uncomment this
        line unless you want to use our api calls, or you need to test and display the application!
         */
        getAPIData();

    }
    //API call
    /*
        #################### APPLICATION CRASHES IF API RATE LIMIT IS REACHED ####################
     */
    public void getAPIData() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://na1.api.riotgames.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ApiClient client = retrofit.create(ApiClient.class);
        //Get API data for ChampionMap from HTTP Response
        Call<ChampionMap> callChampionMap = client.reposForChampionMap();
        callChampionMap.enqueue(new Callback<ChampionMap>() {
            //if successful, get API Data and render it with recyclerview
            @Override
            public void onResponse(Call<ChampionMap> call, Response<ChampionMap> response) {
                ChampionMap responseChampionMap = response.body();
                ArrayList<Champion> responseChampionArrayList = responseChampionMap.getList();
                championsRecyclerView.setAdapter(new ChampionGridAdapter(getApplicationContext(), responseChampionArrayList));
            }
            //if failed, do nothing
            @Override
            public void onFailure(Call<ChampionMap> call, Throwable t) {
            }
        });
    }
}



