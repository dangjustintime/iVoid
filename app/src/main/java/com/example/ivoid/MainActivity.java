package com.example.ivoid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.ivoid.Model.Champion;


public class MainActivity extends AppCompatActivity {

    //views
    public ImageView velKozImageView;
    public ImageButton itemsImageButton;
    public ImageButton championsImageButton;
    public ImageButton updatesImageButton;
    public ImageButton randomImageButton;
    public Champion champ;

    private static String API_KEY = "RGAPI-1a744bc7-e7f3-4963-915c-6ca5b7a92c3b";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //bind views to layout views
        // velKozImageView = (ImageView) findViewById(R.id.vel_koz_image_view);
        itemsImageButton = (ImageButton) findViewById(R.id.image_button_items_main);
        championsImageButton = (ImageButton) findViewById(R.id.image_button_champions_main);
        randomImageButton = (ImageButton) findViewById(R.id.image_button_random_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://na1.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        ApiClient client = retrofit.create(ApiClient.class);
        Call<Champion> call = client.reposForChampion("24");
        call.enqueue(new Callback<Champion>() {
            @Override
            public void onResponse(Call<Champion> call, Response<Champion> response) {
                Champion repos = response.body();
                Toast.makeText(MainActivity.this, repos.getName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Champion> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Response Failed", Toast.LENGTH_SHORT).show();
            }
        });
        */


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

    public void patchNotesClick(View v){

        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://na.leagueoflegends.com/en/news/game-updates/patch/patch-722-notes"));
        //    startActivity(intent);

        String url = "https://na.leagueoflegends.com/en/news/game-updates/patch/patch-!-notes";

        String apicall = "723";
        String replaceString = url.replaceAll("!",apicall );

        if (!replaceString.startsWith("http://") && !replaceString.startsWith("https://"))
            replaceString = "http://" + replaceString;


        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(replaceString));
        startActivity(i);
    }
    public void latestNewsClick(View v){

        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://na.leagueoflegends.com/en/news/game-updates/patch/patch-722-notes"));
        //    startActivity(intent);

        String url = "https://na.leagueoflegends.com/en/news/";

        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
