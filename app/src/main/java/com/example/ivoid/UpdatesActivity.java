package com.example.ivoid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UpdatesActivity extends AppCompatActivity {

    public ImageButton UpdatePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
    }

    public void patchNotesClick(View v){

        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://na.leagueoflegends.com/en/news/game-updates/patch/patch-722-notes"));
        //    startActivity(intent);

        String url = "https://na.leagueoflegends.com/en/news/game-updates/patch/patch-722-notes";

        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
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
