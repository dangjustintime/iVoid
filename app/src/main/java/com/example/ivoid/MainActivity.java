package com.example.ivoid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    //views
    public ImageButton itemsImageButton;
    public ImageButton championsImageButton;
    public ImageButton randomImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //bind views to layout views
        itemsImageButton = (ImageButton) findViewById(R.id.image_button_items_main);
        championsImageButton = (ImageButton) findViewById(R.id.image_button_champions_main);
        randomImageButton = (ImageButton) findViewById(R.id.image_button_random_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //method takes user to the ItemsActivity
    public void itemsClick(View v) {
        //Start ItemsActivity
        Intent intent = new Intent (MainActivity.this, ItemsActivity.class);
        startActivity(intent);
    }

    //method takes user to the ChampionsActivity
    public void championsClick(View v) {
        //Start ChampionActivity
        Intent intent = new Intent (MainActivity.this, ChampionsActivity.class);
        startActivity(intent);
    }

    //method takes user to the RandomWheelActivity
    public void randomClick(View v) {
        //Start RandomWheelActivity
        Intent intent = new Intent (MainActivity.this, RandomWheelActivity.class);
        startActivity(intent);
    }

    //method takes user to a web page to the patch notes for League of Legends
    public void patchNotesClick(View v){
        String url = "https://na.leagueoflegends.com/en/news/game-updates/patch/";
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    //method takes user to a web page for the lastest news for League of Legends
    public void latestNewsClick(View v){
        String url = "https://na.leagueoflegends.com/en/news/";
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
