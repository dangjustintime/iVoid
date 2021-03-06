package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Justin Dang on 10/21/2017.
 */
//model class used for containing Champion Data from Riot API
public class Champion implements Comparable<Champion> {
    //member data
    public class Passive {
        @SerializedName("name")
        private String name;
        @SerializedName("description")
        private String description;
        //getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
    public class ability {
        @SerializedName("name")
        private String name;
        @SerializedName("costBurn")
        private String cost;
        @SerializedName("rangeBurn")
        private String range;
        @SerializedName("description")
        private String description;
        //getters and setters
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
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("key")
    private String key;
    @SerializedName("title")
    private String title;
    @SerializedName("lore")
    private String lore;
    @SerializedName("passive")
    private Passive passive;
    @SerializedName("spells")
    private List<ability> abilities;
    @SerializedName("tags")
    private List<String> tags;
    private String [] splashUrls;
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

    //getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getLore() { return lore; }
    public void setLore(String lore) { this.lore = lore; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
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
    public Passive getPassive() { return passive; }
    public void setPassive(Passive passive) { this.passive = passive; }
    public List<ability> getAbilities() { return abilities; }
    public void setAbilities(List<ability> abilities) { this.abilities = abilities; }
    public String[] getSplashUrls() { return splashUrls; }
    public void setSplashUrls(String[] splashUrls) { this.splashUrls = splashUrls; }

    //sort alphabetically by champion name
    @Override
    public int compareTo(Champion champion) {
        return this.name.compareTo(champion.getName());
    }
}
