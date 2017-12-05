package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Justin Dang on 10/21/2017.
 */

public class Item {
    public class gold {
        @SerializedName("total")
        private int total;
        @SerializedName("sell")
        private int sell;
        //getters and setters
        public int getTotal() { return total; }
        public void setTotal(int total) { this.total = total; }
        public int getSell() { return sell; }
        public void setSell(int sell) { this.sell = sell; }
    }
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("plaintext")
    private String plainText;
    @SerializedName("description")
    private String description;
    @SerializedName("gold")
    private gold price;
    //an array of the items the item builds into, stores the item ids
    @SerializedName("into")
    private ArrayList<Integer> intoItems;
    //getters and settersx
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPlainText() { return plainText; }
    public void setPlainText(String plainText) { this.plainText = plainText; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public gold getPrice() { return price; }
    public void setPrice(gold price) { this.price = price; }
    public ArrayList<Integer> getIntoItems() { return intoItems; }
    public void setIntoItems(ArrayList<Integer> intoItems) { this.intoItems = intoItems; }
}
