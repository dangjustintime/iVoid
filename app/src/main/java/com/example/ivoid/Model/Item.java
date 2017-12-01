package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName("name")
    private String name;
    @SerializedName("plaintext")
    private String plainText;
    @SerializedName("description")
    private String description;
    @SerializedName("gold")
    private gold price;
    //getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPlainText() { return plainText; }
    public void setPlainText(String plainText) { this.plainText = plainText; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public gold getPrice() { return price; }
    public void setPrice(gold price) { this.price = price; }
}
