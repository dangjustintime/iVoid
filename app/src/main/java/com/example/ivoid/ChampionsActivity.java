package com.example.ivoid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.constant.Platform;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

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
}
