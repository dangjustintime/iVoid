package com.example.ivoid;

import android.app.Application;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;

/**
 * Created by Gage on 11/17/2017.
 */

public class MyApi extends Application {
    private RiotApi api;
    public MyApi(){
        ApiConfig config = new ApiConfig().setKey("RGAPI-8a25c8ed-87a7-473d-bab7-a0db87c835e2");
        this.api = new RiotApi(config);
    }
    public RiotApi getApi() {
        return api;
    }
}
