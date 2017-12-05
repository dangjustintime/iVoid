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
API KEYS are put in these strings, at the back of the string starting at RGAPI
 */
public interface ApiClient {
    @GET("/lol/static-data/v3/champions/{id}?locale=en_US&tags=all&api_key=RGAPI-e64c6b47-2582-4936-9ef4-0ab8a09f686e")
    Call<Champion> reposForChampion(@Path("id") String id);
    @GET("/lol/static-data/v3/champions?locale=en_US&dataById=false&api_key=RGAPI-e64c6b47-2582-4936-9ef4-0ab8a09f686e")
    Call<ChampionMap> reposForChampionMap();
    @GET("/lol/static-data/v3/champions/{id}?locale=en_US&tags=all&api_key=RGAPI-e64c6b47-2582-4936-9ef4-0ab8a09f686e")
    Call<Item> reposForItem();
    @GET("/lol/static-data/v3/items?locale=en_US&tags=all&api_key=RGAPI-e64c6b47-2582-4936-9ef4-0ab8a09f686e")
    Call<ItemMap> reposForItemMap();
}
