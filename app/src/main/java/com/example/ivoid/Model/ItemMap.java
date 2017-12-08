package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Justin Dang on 12/1/2017.
 */
//model class used for containing Item Data from Riot API within a map
public class ItemMap {
    //member data
    @SerializedName("data")
    Map<String, Item> itemMap;

    //getters and setters
    public Map<String, Item> getItemMap() { return itemMap; }
    public void setItemMap(Map<String, Item> championMap) { this.itemMap = itemMap; }

    //returns an ArrayList of items alphabetically by item name
    public ArrayList<Item> getList() {
        ArrayList<Item> list = new ArrayList<>();
        Collection<Item> collection = itemMap.values();
        for(Item item : collection) {
            list.add(item);
        }
        Collections.sort(list);
        return list;
    }
}
