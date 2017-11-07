package com.example.ivoid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.ivoid.Dto.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



}
