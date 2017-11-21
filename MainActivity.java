package com.example.ivoid;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.ivoid.Dto.ItemListDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class MainActivity extends AppCompatActivity {

    //views
    public ImageView velKozImageView;
    public ImageButton itemsImageButton;
    public ImageButton championsImageButton;
    public ImageButton updatesImageButton;
    public ImageButton randomImageButton;
    public Button patchNotesClick;
    public Button latestNewsClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //bind views to layout views
        velKozImageView = (ImageView) findViewById(R.id.vel_koz_image_view);
        itemsImageButton = (ImageButton) findViewById(R.id.image_button_items_main);
        championsImageButton = (ImageButton) findViewById(R.id.image_button_champions_main);
        updatesImageButton = (ImageButton) findViewById(R.id.image_button_updates_main);
        randomImageButton = (ImageButton) findViewById(R.id.image_button_random_main);
        patchNotesClick = (Button) findViewById(R.id.patch_notes_button);
        latestNewsClick = (Button) findViewById(R.id.latest_news_button);

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


    public void randomClick(View v) {
        //Start RandomWheelActivity
        Intent intent = new Intent (MainActivity.this, RandomWheelActivity.class);
        startActivity(intent);
    }



    public void patchNotesClick(View v){

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


        String url = "https://na.leagueoflegends.com/en/news/";

        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
