package com.example.ivoid;

import com.example.ivoid.Model.Champion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by alexcoalla on 12/3/17.
 */
/**
 * Place key after equals starting at &api_key= ...
 */


public interface ChampionGGAPI {
    //get winRate, playRate, gamesPlayed, percentRolePlayed, banRate, role
        @GET("/champions/{id}?api_key=2c2b2ef84cd5528a186e2c77efac5fe1")
    Call<Champion> reposforChampionAnalytics(@Path("id") String id);
}
