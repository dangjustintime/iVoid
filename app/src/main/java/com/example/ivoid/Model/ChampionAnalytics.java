package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Justin Dang on 12/6/2017.
 */

public class ChampionAnalytics {
    public class Statistic {
        @SerializedName("val")
        private String val;
        public String getVal() { return val; }
        public void setVal(String val) { this.val = val; }
    }
    @SerializedName("winPercent")
    private Statistic winPercent;
    @SerializedName("playPercent")
    private Statistic playPercent;
    @SerializedName("banRate")
    private Statistic banPercent;
    @SerializedName("kills")
    private Statistic kills;
    @SerializedName("deaths")
    private Statistic deaths;
    @SerializedName("assists")
    private Statistic assists;

    //getters and setters
    public Statistic getWinPercent() { return winPercent; }
    public void setWinPercent(Statistic winPercent) { this.winPercent = winPercent; }
    public Statistic getPlayPercent() { return playPercent; }
    public void setPlayPercent(Statistic playPercent) { this.playPercent = playPercent; }
    public Statistic getBanPercent() { return banPercent; }
    public void setBanPercent(Statistic banPercent) { this.banPercent = banPercent; }
    public Statistic getKills() { return kills; }
    public void setKills(Statistic kills) { this.kills = kills; }
    public Statistic getDeaths() { return deaths; }
    public void setDeaths(Statistic deaths) { this.deaths = deaths; }
    public Statistic getAssists() { return assists; }
    public void setAssists(Statistic assists) { this.assists = assists; }
}
