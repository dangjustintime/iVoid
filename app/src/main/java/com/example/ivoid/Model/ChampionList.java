package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Justin Dang on 11/30/2017.
 */

public class ChampionList {
    @SerializedName("data")
    private List<Champion> list;
    //getters and setters
    public List<Champion> getList() { return list; }
    public void setList(List<Champion> list) { this.list = list; }
}
