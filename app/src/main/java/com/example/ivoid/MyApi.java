package com.example.ivoid;

import android.app.Application;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;

/**
 * Created by Gage on 11/17/2017.
 */
/*This is a Java Wrapper Implementation of the API calls. Isn't used for this iteration, but may be useful in the future!

 */
public class MyApi extends Application {
    private RiotApi api;
    public MyApi(){
        ApiConfig config = new ApiConfig().setKey("RGAPI-f4baf993-eab3-43ba-9f3e-af3b6935613b");
        this.api = new RiotApi(config);
    }
    public RiotApi getApi() {
        return api;
    }
}
