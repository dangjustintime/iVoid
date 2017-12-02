package com.example.ivoid;

import com.example.ivoid.Model.Champion;
import com.example.ivoid.Model.ChampionMap;
import com.example.ivoid.Model.Item;
import com.example.ivoid.Model.ItemMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Justin Dang on 11/30/2017.
 */

public interface ApiClient {
    @GET("/lol/static-data/v3/champions/{id}?locale=en_US&tags=all&api_key=RGAPI-3a0b7441-0440-42e9-9546-b0c91bc987fb")
    Call<Champion> reposForChampion(@Path("id") String id);
    @GET("/lol/static-data/v3/champions?locale=en_US&dataById=false&api_key=RGAPI-3a0b7441-0440-42e9-9546-b0c91bc987fb")
    Call<ChampionMap> reposForChampionMap();
    @GET("/lol/static-data/v3/champions/{id}?locale=en_US&tags=all&api_key=RGAPI-3a0b7441-0440-42e9-9546-b0c91bc987fb")
    Call<Item> reposForItem();
    @GET("/lol/static-data/v3/items?locale=en_US&tags=all&api_key=RGAPI-3a0b7441-0440-42e9-9546-b0c91bc987fb")
    Call<ItemMap> reposForItemMap();
}
