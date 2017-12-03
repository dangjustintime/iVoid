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
    @GET("/lol/static-data/v3/champions/{id}?locale=en_US&tags=all&api_key=RGAPI-e8fc745c-c4b1-4e0a-b27e-d8bdacd1c2f3")
    Call<Champion> reposForChampion(@Path("id") String id);
    @GET("/lol/static-data/v3/champions?locale=en_US&dataById=false&api_key=RGAPI-e8fc745c-c4b1-4e0a-b27e-d8bdacd1c2f3")
    Call<ChampionMap> reposForChampionMap();
    @GET("/lol/static-data/v3/champions/{id}?locale=en_US&tags=all&api_key=RGAPI-e8fc745c-c4b1-4e0a-b27e-d8bdacd1c2f3")
    Call<Item> reposForItem();
    @GET("/lol/static-data/v3/items?locale=en_US&tags=all&api_key=RGAPI-e8fc745c-c4b1-4e0a-b27e-d8bdacd1c2f3")
    Call<ItemMap> reposForItemMap();
}
