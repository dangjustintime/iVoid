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
        ApiConfig config = new ApiConfig().setKey("RGAPI-64ad4b28-5e22-4e48-8c20-d57a981fdec9");
        this.api = new RiotApi(config);
    }
    public RiotApi getApi() {
        return api;
    }
}
