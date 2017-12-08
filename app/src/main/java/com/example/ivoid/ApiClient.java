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
/*
        ####################APPLICATION CRASHES IF API RATE LIMIT IS REACHED####################
        API KEYS are put in these strings, at the back of the string starting at RGAPI

 */
public interface ApiClient {
    @GET("/lol/static-data/v3/champions/{id}?locale=en_US&tags=all&api_key=RGAPI-75b684dd-55e7-43d9-aff5-b7a86c2d11ce")
    Call<Champion> reposForChampion(@Path("id") String id);
    @GET("/lol/static-data/v3/champions?locale=en_US&dataById=false&api_key=RGAPI-75b684dd-55e7-43d9-aff5-b7a86c2d11ce")
    Call<ChampionMap> reposForChampionMap();
    @GET("/lol/static-data/v3/items/{id}?locale=en_US&tags=all&api_key=RGAPI-75b684dd-55e7-43d9-aff5-b7a86c2d11ce")
    Call<Item> reposForItem(@Path("id") String id);
    @GET("/lol/static-data/v3/items?locale=en_US&tags=all&api_key=RGAPI-75b684dd-55e7-43d9-aff5-b7a86c2d11ce")
    Call<ItemMap> reposForItemMap();
}
