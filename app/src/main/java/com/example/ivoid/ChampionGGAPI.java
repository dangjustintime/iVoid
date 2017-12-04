package com.example.ivoid;

import com.example.ivoid.Model.GGObjects;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by alexcoalla on 12/3/17.
 */
/**
 * Place key after equals starting at &api_key= ...
 */


public interface ChampionGGAPI {

    @GET("http://api.champion.gg/v2/champions?elo=SILVER&Data=%20patch,matchups&limit=1,champData&api_key=2c2b2ef84cd5528a186e2c77efac5fe1")
    Call <GGObjects>  DefaultObject();
}
