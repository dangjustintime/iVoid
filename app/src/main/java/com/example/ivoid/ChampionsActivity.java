package com.example.ivoid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

public class ChampionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);
        FetchSummonerTask summonerTask = new FetchSummonerTask();
        summonerTask.execute("tryndamere");
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
