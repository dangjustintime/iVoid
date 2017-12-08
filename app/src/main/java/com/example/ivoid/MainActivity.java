package com.example.ivoid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.ivoid.Model.Champion;
import com.example.ivoid.Model.ChampionAnalytics;
import com.example.ivoid.Model.ChampionMap;
import com.example.ivoid.Model.Item;
import com.example.ivoid.Model.ItemMap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    //views
    public ImageView velKozImageView;
    public ImageButton itemsImageButton;
    public ImageButton championsImageButton;
    public ImageButton updatesImageButton;
    public ImageButton randomImageButton;


    private static String API_KEY = "RGAPI-04f6ba43-8c0b-448e-8d14-065b96eb5c7b";
    private ChampionMap championMap;
    private ArrayList<Champion> championArrayList;
    private ItemMap itemMap;
    private ArrayList<Item> itemArrayList;
    private Retrofit retrofit = null;
    private Retrofit championGGRetrofit = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //bind views to layout views
        // velKozImageView = (ImageView) findViewById(R.id.vel_koz_image_view);
        itemsImageButton = (ImageButton) findViewById(R.id.image_button_items_main);
        championsImageButton = (ImageButton) findViewById(R.id.image_button_champions_main);
        randomImageButton = (ImageButton) findViewById(R.id.image_button_random_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getChampionGGAPIData();


    }

    public void itemsClick(View v) {
        //Start ItemsActivity
        Intent intent = new Intent (MainActivity.this, ItemsActivity.class);
        startActivity(intent);
    }

    public void championsClick(View v) {
        //Start ItemsActivity
        Intent intent = new Intent (MainActivity.this, ChampionsActivity.class);
        startActivity(intent);
    }


    public void randomClick(View v) {
        //Start RandomWheelActivity
        Intent intent = new Intent (MainActivity.this, RandomWheelActivity.class);
        startActivity(intent);
    }

    public void getChampGG(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.champion.gg/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ChampionGGAPI GGapi = retrofit.create(ChampionGGAPI.class);

    }


    public void patchNotesClick(View v){
        String patch = "";      //insert apicall patch # here

        String url = "https://na.leagueoflegends.com/en/news/game-updates/patch/";
        //String url = "https://na.leagueoflegends.com/en/news/game-updates/patch/patch-!-notes";
        String replaceString = url.replaceAll("!", patch );

        if (!replaceString.startsWith("http://") && !replaceString.startsWith("https://"))
            replaceString = "http://" + replaceString;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(replaceString));
        startActivity(i);
    }


    public void latestNewsClick(View v){

        String url = "https://na.leagueoflegends.com/en/news/";

        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
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
        //get Item Map
        Call<ItemMap> callItemMap = client.reposForItemMap();
        callItemMap.enqueue(new Callback<ItemMap>() {
            @Override
            public void onResponse(Call<ItemMap> call, Response<ItemMap> response) {
                ItemMap responseItemMap = response.body();
                ArrayList<Item> responseItemArrayList = responseItemMap.getList();
                //Toast.makeText(MainActivity.this, "Champion Response Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ItemMap> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "Champion Response Failed", Toast.LENGTH_LONG).show();
            }
        });
        //get champion map
        Call<ChampionMap> callChampionMap = client.reposForChampionMap();
        callChampionMap.enqueue(new Callback<ChampionMap>() {
            @Override
            public void onResponse(Call<ChampionMap> call, Response<ChampionMap> response) {
                ChampionMap responseChampionMap = response.body();
                ArrayList<Champion> responseChampionArrayList = responseChampionMap.getList();
                //Toast.makeText(MainActivity.this, "Champion Response Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ChampionMap> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "Champion Response Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getChampionGGAPIData() {
        if(championGGRetrofit == null) {
            championGGRetrofit = new Retrofit.Builder()
                    .baseUrl("http://api.champion.gg")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ChampionGGAPI client = championGGRetrofit.create(ChampionGGAPI.class);
        //call for championMap
        Call<List<ChampionAnalytics>> callChampionAnalytics = client.reposForChampionAnalytics("Jax");
        callChampionAnalytics.enqueue(new Callback<List<ChampionAnalytics>>() {
            @Override
            public void onResponse(Call<List<ChampionAnalytics>> call, Response<List<ChampionAnalytics>> response) {
                List<ChampionAnalytics> responseChampionAnalytics = response.body();

                /*
                championWinRate.setText(responseChampionAnalytics.get(0).getWinPercent().getVal());
                championPlayRate.setText(responseChampionAnalytics.get(0).getPlayPercent().getVal());
                championBanRate.setText(responseChampionAnalytics.get(0).getBanPercent().getVal());
                championKills.setText(responseChampionAnalytics.get(0).getKills().getVal());
                championDeaths.setText(responseChampionAnalytics.get(0).getDeaths().getVal());
                championAssists.setText(responseChampionAnalytics.get(0).getAssists().getVal());
                */
                //Toast.makeText(MainActivity.this, responseChampionAnalytics.get(0).getDeaths().getVal(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<List<ChampionAnalytics>> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "ChampionGG Response Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

}
