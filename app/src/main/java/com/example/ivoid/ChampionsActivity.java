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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);
        FetchSummonerTask summonerTask = new FetchSummonerTask();
        summonerTask.execute("tryndamere");

        championsEditText = (EditText) findViewById(R.id.edit_text_search_item);
        championCardView = (CardView) findViewById(R.id.champion_card_view);
        championCardView.setCardBackgroundColor(R.color.lightGray);
        /*
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://na1.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        ApiClient client = retrofit.create(ApiClient.class);
        Call<ChampionMap> call = client.reposForChampionMap();
        call.enqueue(new Callback<ChampionMap>() {
            @Override
            public void onResponse(Call<ChampionMap> call, Response<ChampionMap> response) {
                championMap = response.body();
                championArrayList = championMap.getList();
                Toast.makeText(ChampionsActivity.this, championArrayList.get(80).getTitle(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ChampionMap> call, Throwable t) {
                Toast.makeText(ChampionsActivity.this, "Response Failed", Toast.LENGTH_LONG).show();
            }
        });




        //recycler view
        championsRecyclerView = (RecyclerView) findViewById(R.id.champion_grid_recycler_view);
        championsRecyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));
        championRecyclerAdapter = new ChampionGridAdapter(this, championArrayList);
        championsRecyclerView.setAdapter(championRecyclerAdapter);
        *




    }
    public class FetchSummonerTask extends AsyncTask<String, Void, Summoner> {

        MyApi api = new MyApi();
        @Override
        protected Summoner doInBackground(String... params) {

            try {
                Summoner summoner = ((api.getApi()
                        .getSummonerByName(Platform.NA, params[0])));
                if(summoner != null) {
                    return summoner;
                }
            } catch (RiotApiException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Summoner result){
        super.onPostExecute(result);
        if(result != null)
            Toast.makeText(ChampionsActivity.this, String.valueOf(result.getSummonerLevel()), Toast.LENGTH_SHORT).show();}
    }

    public void championInfoClick(View v) {
        //Start ItemsActivity
        Intent intent = new Intent (ChampionsActivity.this, ChampionInfoActivity.class);
        startActivity(intent);
    }
}
