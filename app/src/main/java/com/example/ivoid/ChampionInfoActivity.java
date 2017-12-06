package com.example.ivoid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivoid.Model.Champion;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChampionInfoActivity extends AppCompatActivity {
    //intent data
    private int championId;
    private Retrofit retrofit = null;

    //views
    private TextView championNameTextView;
    private TextView championTitleTextView;
    private ImageView championRoleIconImageView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_info);
        championId = getIntent().getIntExtra("championId", 0);



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
        championRoleIconImageView = (ImageView) findViewById(R.id.champion_role_icon_image_view);
        championSplashArt1 = (ImageView) findViewById(R.id.champion_splash_image_view);
        championSplashArt2 = (ImageView) findViewById(R.id.champion_splash_image_view2);


        Picasso.with(getApplicationContext())
                .load(getIntent().getStringExtra("splashUrl1"))
                .into(championSplashArt1);
        Picasso.with(getApplicationContext())
                .load(getIntent().getStringExtra("splashUrl2"))
                .into(championSplashArt2);

        //API call
        getAPIData();

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
                String [] tag = responseChampion.getTags();
                if(tag[0] == "Fighter") {
                    championRoleIconImageView.setBackground(getResources().getDrawable(R.drawable.fighter));
                } else if(tag[0] == "Tank") {
                    championRoleIconImageView.setBackground(getResources().getDrawable(R.drawable.tank));
                } else if(tag[0] == "Marksman") {
                    championRoleIconImageView.setBackground(getResources().getDrawable(R.drawable.marksman));
                } else if(tag[0] == "Support") {
                    championRoleIconImageView.setBackground(getResources().getDrawable(R.drawable.support_icon));
                } else if(tag[0] == "Assassin") {
                    championRoleIconImageView.setBackground(getResources().getDrawable(R.drawable.slayer));
                }


                Toast.makeText(ChampionInfoActivity.this, "Champion Response Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Champion> call, Throwable t) {
                Toast.makeText(ChampionInfoActivity.this, "Champion Response Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
