package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Justin Dang on 11/30/2017.
 */

public class ChampionMap {
    @SerializedName("data")
    private Map<String, Champion> championMap;
    //getters and setters
    public Map<String, Champion> getChampionMap() { return championMap; }
    public void setChampionMap(Map<String, Champion> championMap) { this.championMap = championMap; }

    public List<Champion> getList() {
        List<Champion> list;
        Set<Map.Entry<String, Champion>> set = championMap.entrySet();





    }
}
