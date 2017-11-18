package com.example.ivoid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.ivoid.Dto.ItemListDto;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

public class ItemsActivity extends AppCompatActivity {
    //views
    private RecyclerView itemsRecyclerView;
    private RecyclerView.Adapter itemsRecyclerViewAdapter;
    private RecyclerView.LayoutManager itemsRecyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        itemsRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_items);

        itemsRecyclerView.hasFixedSize();
        itemsRecyclerViewLayoutManager = new LinearLayoutManager(this);
        itemsRecyclerView.setLayoutManager(itemsRecyclerViewLayoutManager);
    }

    public class FetchItemsTask extends AsyncTask<String, Void, ItemListDto> {

        MyApi api = new MyApi();
        @Override
        protected ItemListDto doInBackground(String... params) {

            try {
                ItemListDto itemListDto = ((api.getApi().getDataItemList(Platform.NA)));
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
                Toast.makeText(ItemsActivity.this, String.valueOf(result.getSummonerLevel()), Toast.LENGTH_SHORT).show();}
    }

}
