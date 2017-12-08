package com.example.ivoid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ivoid.Model.Item;
import com.example.ivoid.Model.ItemGridAdapter;
import com.example.ivoid.Model.ItemMap;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  ItemsActivity extends AppCompatActivity {
    //views
    private RecyclerView itemsRecyclerView;
    Retrofit retrofit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        //generate and bind recycler view
        itemsRecyclerView = (RecyclerView) findViewById(R.id.item_grid_recycler_view);
        itemsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        //call API
        /* This line of code forces the ItemsActivity to make API calls, four to be exact. Do not uncomment this
        line unless you want to use our api calls, or you need to test and display the application!
         */
        getAPIData();

    }

    //API call
    /*
        #################### APPLICATION CRASHES IF API RATE LIMIT IS REACHED ####################
     */
    public void getAPIData() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://na1.api.riotgames.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ApiClient client = retrofit.create(ApiClient.class);
        //Get API data for ItemMap from HTTP Response
        Call<ItemMap> callItemMap = client.reposForItemMap();
        callItemMap.enqueue(new Callback<ItemMap>() {
            //if request successful, get API Data
            @Override
            public void onResponse(Call<ItemMap> call, Response<ItemMap> response) {
                ItemMap responseItemMap = response.body();
                ArrayList<Item> temp = responseItemMap.getList();
                ArrayList<Item> responseItemArrayList = new ArrayList<>();
                boolean flag = false;
                for(int i = 0; i < temp.size(); i++)
                {
                    if(temp.get(i).getInStore())
                    {
                        for(int j = 0; j < responseItemArrayList.size(); j++)
                        {
                            if(responseItemArrayList.get(j).getName().equals(temp.get(i).getName()))
                            {
                                flag = true;
                                j = responseItemArrayList.size();
                            }
                        }
                        if(!flag)
                        {
                            responseItemArrayList.add(temp.get(i));
                        }
                        flag = false;
                    }
                }
                itemsRecyclerView.setAdapter(new ItemGridAdapter(getApplicationContext(), responseItemArrayList));
            }
            //if request failed, do nothing
            @Override
            public void onFailure(Call<ItemMap> call, Throwable t) {
            }
        });
    }

}
