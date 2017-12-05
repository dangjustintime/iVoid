package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Justin Dang on 10/21/2017.
 */

public class Champion implements Comparable<Champion> {
    public class ability {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("costBurn")
        private String cost;
        @SerializedName("rangeBurn")
        private String range;
        @SerializedName("description")
        private String description;
        //getters and setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name;}
        public void setName(String name) { this.name = name; }
        public String getCost() { return cost; }
        public void setCost(String cost) { this.cost = cost; }
        public String getRange() { return range; }
        public void setRange(String range) { this.range = range; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
    //name, title, lore
    @SerializedName("name")
    private String name;
    @SerializedName("title")
    private String title;
    @SerializedName("lore")
    private String lore;
    @SerializedName("spells")
    private List<ability> abilities;
    private String imageUrl;
    //stats
    @SerializedName("attackdamage")
    private double attackDamage;
    @SerializedName("attackspeedoffset")
    private double attackSpeedOffset;
    @SerializedName("armor")
    private double armor;
    @SerializedName("hp")
    private double hp;
    @SerializedName("spellblock")
    private double spellBlock;
    @SerializedName("movespeed")
    private double moveSpeed;
    @SerializedName("hpregen")
    private double hpRegen;
    @SerializedName("winRate")
    private double winRate;
    @SerializedName("playRate")
    private double playRate;
    @SerializedName("gamesPlayed")
    private int gamesPlayed;
    @SerializedName("percentRolePlayed")
    private double percentRolePlayed;
    @SerializedName("banRate")
    private double banRate;
    @SerializedName("role")
    private String role;
    //getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getLore() { return lore; }
    public void setLore(String lore) { this.lore = lore; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public double getAttackDamage() { return attackDamage; }
    public void setAttackDamage(double attackDamage) { this.attackDamage = attackDamage; }
    public double getAttackSpeedOffset() { return attackSpeedOffset; }
    public void setAttackSpeedOffset(double attackSpeedOffset) { this.attackSpeedOffset = attackSpeedOffset; }
    public double getArmor() { return armor; }
    public void setArmor(double armor) { this.armor = armor; }
    public double getHp() { return hp; }
    public void setHp(double hp) { this.hp = hp; }
    public double getSpellBlock() { return spellBlock; }
    public void setSpellBlock(double spellBlock) { this.spellBlock = spellBlock; }
    public double getMoveSpeed() { return moveSpeed; }
    public void setMoveSpeed(double moveSpeed) { this.moveSpeed = moveSpeed; }
    public double getHpRegen() { return hpRegen; }
    public void setHpRegen(double hpRegen) { this.hpRegen = hpRegen; }
    public List<ability> getAbilities() { return abilities; }
    public void setAbilities(List<ability> abilities) { this.abilities = abilities; }
    //analytics
    public  double getWinRate() { return winRate; }
    public void setWinRate(double winRate) { this.winRate = winRate; }
    public double getPlayRate() { return playRate; }
    public void setPlayRate(double playRate) { this.playRate = playRate; }
    public int getGamesPlayed() { return gamesPlayed; }
    public void setGamesPlayed(int gamesPlayed) { this.gamesPlayed = gamesPlayed; }
    public double getPercentRolePlayed() { return percentRolePlayed; }
    public void setPercentRolePlayed(double percentRolePlayed) { this.percentRolePlayed = percentRolePlayed; }
    public double getBanRate() { return banRate; }
    public void setBanRate(double banRate) {this.banRate = banRate; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public int compareTo(Champion champion) {
        return this.name.compareTo(champion.getName());
    }

}
