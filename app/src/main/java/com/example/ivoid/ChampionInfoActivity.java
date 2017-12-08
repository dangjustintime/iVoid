package com.example.ivoid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivoid.Model.Champion;
import com.example.ivoid.Model.ChampionAnalytics;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChampionInfoActivity extends AppCompatActivity {
    //intent data
    private int championId;
    private String championKey;
    private Retrofit leagueRetrofit = null;
    private Retrofit championGGRetrofit = null;;

    //views
    private TextView championNameTextView;
    private TextView championTitleTextView;
    private ImageView championSplashArt1;
    private ImageView championSplashArt2;
    private TextView championPassiveName;
    private TextView championPassiveDescription;
    private TextView championQAbilityName;
    private TextView championQAbilityDescription;
    private TextView championWAbilityName;
    private TextView championWAbilityDescription;
    private TextView championEAbilityName;
    private TextView championEAbilityDescription;
    private TextView championRAbilityName;
    private TextView championRAbilityDescription;
    private TextView championWinRate;
    private TextView championPlayRate;
    private TextView championBanRate;
    private TextView championKills;
    private TextView championDeaths;
    private TextView championAssists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_info);
        championId = getIntent().getIntExtra("championId", 0);
        championKey = getIntent().getStringExtra("championKey");
        //bindviews
        championNameTextView = (TextView) findViewById(R.id.champion_name_text_view);
        championTitleTextView = (TextView) findViewById(R.id.champion_title_text_view);
        championPassiveName = (TextView) findViewById(R.id.champion_passive_title_text_view);
        championPassiveDescription = (TextView) findViewById(R.id.champion_passive_description_text_view);
        championQAbilityName = (TextView) findViewById(R.id.champion_q_title_text_view);
        championQAbilityDescription = (TextView) findViewById(R.id.champion_q_description_text_view);
        championWAbilityName = (TextView) findViewById(R.id.champion_w_title_text_view);
        championWAbilityDescription = (TextView) findViewById(R.id.champion_w_description_text_view);
        championEAbilityName = (TextView) findViewById(R.id.champion_e_title_text_view);
        championEAbilityDescription = (TextView) findViewById(R.id.champion_e_description_text_view);
        championRAbilityName = (TextView) findViewById(R.id.champion_r_title_text_view);
        championRAbilityDescription = (TextView) findViewById(R.id.champion_r_description_text_view);
        championSplashArt1 = (ImageView) findViewById(R.id.champion_splash_image_view);
        championSplashArt2 = (ImageView) findViewById(R.id.champion_splash_image_view2);
        championWinRate = (TextView) findViewById(R.id.champion_win_rate_text_view);
        championPlayRate = (TextView) findViewById(R.id.champion_play_rate_text_view);
        championBanRate = (TextView) findViewById(R.id.champion_ban_rate_text_view);
        championKills = (TextView) findViewById(R.id.champion_kills_text_view);
        championDeaths = (TextView) findViewById(R.id.champion_deaths_text_view);
        championAssists = (TextView) findViewById(R.id.champion_assists_text_view);


        //image loader
        Picasso.with(getApplicationContext())
                .load(getIntent().getStringExtra("splashUrl1"))
                .into(championSplashArt1);
        Picasso.with(getApplicationContext())
                .load(getIntent().getStringExtra("splashUrl2"))
                .into(championSplashArt2);

        //API call
        getLeagueAPIData();
        getChampionGGAPIData();
    }
    //API call
    public void getLeagueAPIData() {
        if(leagueRetrofit == null) {
            leagueRetrofit = new Retrofit.Builder()
                    .baseUrl("https://na1.api.riotgames.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ApiClient client = leagueRetrofit.create(ApiClient.class);
        //call for championMap
        Call<Champion> callChampion = client.reposForChampion(String.valueOf(championId));
        callChampion.enqueue(new Callback<Champion>() {
            @Override
            public void onResponse(Call<Champion> call, Response<Champion> response) {
                Champion responseChampion = response.body();
                championNameTextView.setText(responseChampion.getName());
                championTitleTextView.setText(responseChampion.getTitle());
                championPassiveName.setText(responseChampion.getPassive().getName());
                championPassiveDescription.setText(responseChampion.getPassive().getDescription());
                championQAbilityName.setText(responseChampion.getAbilities().get(0).getName());
                championQAbilityDescription.setText(responseChampion.getAbilities().get(0).getDescription());
                championWAbilityName.setText(responseChampion.getAbilities().get(1).getName());
                championWAbilityDescription.setText(responseChampion.getAbilities().get(1).getDescription());
                championEAbilityName.setText(responseChampion.getAbilities().get(2).getName());
                championEAbilityDescription.setText(responseChampion.getAbilities().get(2).getDescription());
                championRAbilityName.setText(responseChampion.getAbilities().get(3).getName());
                championRAbilityDescription.setText(responseChampion.getAbilities().get(3).getDescription());
            }
            @Override
            public void onFailure(Call<Champion> call, Throwable t) { }
        });
    }
    //API call
    public void getChampionGGAPIData() {
        if(championGGRetrofit == null) {
            championGGRetrofit = new Retrofit.Builder()
                    .baseUrl("http://api.champion.gg")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ChampionGGAPI client = championGGRetrofit.create(ChampionGGAPI.class);
        //call for championMap
        Call<List<ChampionAnalytics>> callChampionAnalytics = client.reposForChampionAnalytics(championKey);
        callChampionAnalytics.enqueue(new Callback<List<ChampionAnalytics>>() {
            @Override
            public void onResponse(Call<List<ChampionAnalytics>> call, Response<List<ChampionAnalytics>> response) {
                List<ChampionAnalytics> responseChampionAnalytics = response.body();
                championWinRate.setText(responseChampionAnalytics.get(0).getWinPercent().getVal() + "%");
                championPlayRate.setText(responseChampionAnalytics.get(0).getPlayPercent().getVal() + "%");
                championBanRate.setText(responseChampionAnalytics.get(0).getBanPercent().getVal() + "%");
                championKills.setText(responseChampionAnalytics.get(0).getKills().getVal());
                championDeaths.setText(responseChampionAnalytics.get(0).getDeaths().getVal());
                championAssists.setText(responseChampionAnalytics.get(0).getAssists().getVal());
                //Toast.makeText(ChampionInfoActivity.this, "ChampionGG Response Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<List<ChampionAnalytics>> call, Throwable t) {
                //Toast.makeText(ChampionInfoActivity.this, "ChampionGG Response Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
