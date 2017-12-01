package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Justin Dang on 12/1/2017.
 */

public class ItemMap {
    @SerializedName("data")
    Map<String, Item> itemMap;
    //getters and setters
    public Map<String, Item> getItemMap() { return itemMap; }
    public void setItemMap(Map<String, Item> championMap) { this.itemMap = itemMap; }

    public ArrayList<Item> getList() {
        ArrayList<Item> list = new ArrayList<>();
        Collection<Item> collection = itemMap.values();
        for(Item item : collection) {
            list.add(item);
        }
        return list;
    }
}
