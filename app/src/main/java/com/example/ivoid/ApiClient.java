package com.example.ivoid;

import com.example.ivoid.Model.Champion;
import com.example.ivoid.Model.ChampionMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Justin Dang on 11/30/2017.
 */

public interface ApiClient {
    String API_KEY = "RGAPI-8da3f5ce-b8df-4473-9ea1-e345f60387a0";
    @GET("/lol/static-data/v3/champions/{id}?locale=en_US&tags=all&api_key=RGAPI-8da3f5ce-b8df-4473-9ea1-e345f60387a0")
    Call<Champion> reposForChampion(@Path("id") String id);
    @GET("/lol/static-data/v3/champions?locale=en_US&dataById=false&api_key=RGAPI-8da3f5ce-b8df-4473-9ea1-e345f60387a0")
    Call<ChampionMap> reposForChampionMap();
}
