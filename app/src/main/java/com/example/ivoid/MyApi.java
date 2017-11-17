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
        ApiConfig config = new ApiConfig().setKey("RGAPI-a398042b-97b2-4372-a7f4-78dfcf138e15");
        this.api = new RiotApi(config);
    }

    public RiotApi getApi() {
        return api;
    }
}
