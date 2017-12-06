package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Justin Dang on 12/6/2017.
 */

public class ChampionAnalytics {
    public class Id {
        @SerializedName("championId")
        private int championId;
        @SerializedName("role")
        private String role;
        //getters and setters
        public int getChampionId() { return championId; }
        public void setChampionId(int championId) { this.championId = championId; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
    @SerializedName("id")
    private Id id;
    @SerializedName("elo")
    private String elo;
    @SerializedName("patch")
    private String patch;
    @SerializedName("winRate")
    private double winRate;
    @SerializedName("playRate")
    private double playRate;
    @SerializedName("gamesPlayed")
    private int gamesPlayed;
    @SerializedName("banRate")
    private double banRate;
    //getters and setters
    public Id getId() { return id; }
    public void setId(Id id) { this.id = id; }
    public String getElo() { return elo; }
    public void setElo(String elo) { this.elo = elo; }
    public String getPatch() { return patch; }
    public void setPatch(String patch) { this.patch = patch; }
    public double getWinRate() { return winRate; }
    public void setWinRate(double winRate) { this.winRate = winRate; }
    public double getPlayRate() { return playRate; }
    public void setPlayRate(double playRate) { this.playRate = playRate; }
    public int getGamesPlayed() { return gamesPlayed; }
    public void setGamesPlayed(int gamesPlayed) { this.gamesPlayed = gamesPlayed; }
    public double getBanRate() { return banRate; }
    public void setBanRate(double banRate) { this.banRate = banRate; }
}
