package com.example.ivoid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.ivoid.Dto.ItemListDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class MainActivity extends AppCompatActivity {

    //views
    public ImageButton itemsImageButton;
    public ImageButton championsImageButton;
    public ImageButton updatesImageButton;
    public ImageButton randomImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //bind views to layout views
        itemsImageButton = (ImageButton) findViewById(R.id.image_button_items_main);
        championsImageButton = (ImageButton) findViewById(R.id.image_button_champions_main);
        updatesImageButton = (ImageButton) findViewById(R.id.image_button_updates_main);
        randomImageButton = (ImageButton) findViewById(R.id.image_button_random_main);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void updatesClick(View v) {
        //Start ItemsActivity
        Intent intent = new Intent (MainActivity.this, UpdatesActivity.class);
        startActivity(intent);
    }

    public void randomClick(View v) {
        //Start RandomWheelActivity
        Intent intent = new Intent (MainActivity.this, RandomWheelActivity.class);
        startActivity(intent);
    }
    public void updateData(View v) throws IOException {
        try(Reader reader = new InputStreamReader(MainActivity.class.getResourceAsStream("assets/items.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            ItemListDto itemList = gson.fromJson(reader, ItemListDto.class);
            Log.v("ITEM LIST: ",itemList.version);

        }
    }
    public void updatePage(View v){

        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://na.leagueoflegends.com/en/news/game-updates/patch/patch-722-notes"));
        //    startActivity(intent);

        String url = "https://na.leagueoflegends.com/en/news/game-updates/patch/patch-722-notes";

        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);



    }


}
