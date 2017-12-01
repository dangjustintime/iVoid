package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Justin Dang on 11/30/2017.
 */

public class ChampionMap {
    @SerializedName("data")
    private Map<String, Champion> championMap;
    //getters and setters
    public Map<String, Champion> getChampionMap() { return championMap; }
    public void setChampionMap(Map<String, Champion> championMap) { this.championMap = championMap; }

    public ArrayList<Champion> getList() {
        ArrayList<Champion> list = new ArrayList<>();
        Collection<Champion> collection = championMap.values();
        for(Champion champion : collection) {
            list.add(champion);
        }
        return list;
    }
}
