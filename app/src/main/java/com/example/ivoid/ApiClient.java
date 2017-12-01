package com.example.ivoid;

import com.example.ivoid.Model.Champion;
import com.example.ivoid.Model.ChampionList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Justin Dang on 11/30/2017.
 */

public interface ApiClient {
    String API_KEY = "RGAPI-1a744bc7-e7f3-4963-915c-6ca5b7a92c3b";
    @GET("/lol/static-data/v3/champions/{id}?locale=en_US&tags=all&api_key=RGAPI-1a744bc7-e7f3-4963-915c-6ca5b7a92c3b")
    Call<Champion> reposForChampion(@Path("id") String id);
    @GET("/lol/static-data/v3/champions?locale=en_US&dataById=false&api_key=RGAPI-1a744bc7-e7f3-4963-915c-6ca5b7a92c3b")
    Call<ChampionList> reposForChampionList();
}
