package lol_API_Key;
import java.util.*;


public class Items{

    // **Objects contrains item list data.

    data Map[string, ItemDto];
    String version;
    tree List[ItemTreeDto];
    groups List[GroupDto];
    String type;

    // **Objects contains item tree data.

    String header;
    List[String] tags;

    // **Objects contains item data.

    GoldDto gold;
    String plaintext;
    boolean hideFromAll;
    boolean inStore;
    List[String] into;
    int id;
    InventoryDataStatsDto stats;
    String colloq;
    Map[String, boolean] maps;
    int specialRecipe;
    imageDto images;
    String description;
    List[String] tags;
    Map[String, String] effect;
    String requiredChampion;
    List[String] from;
    String group;
    boolean consumeOnFull;
    String name;
    boolean consumed;
    String sanitizedDescription;
    int depth;
    int stacks;

    // **Objects contains item gold data.

    int sell;
    int total;
    int base;
    boolean purchaseable;

    // **Objects contains stats for inventory.

    double PercentCritDamageMod;
    double PercentSpellBlockMod;
    double PercentHPRegenMod;
    double PercentMovementSpeedMod;
    double FlatSpellBlockMod;
    double FlatCritDamageMod;
    double FlatEnergyPoolMod;
    double PercentLifeStealMod;
    double FlatMPPoolMod;
    double FlatMovementSpeedMod;
    double PercentAttackSpeedMod;
    double FlatBlockMod;
    double PercentBlockMod;
    double FlatEnergyRegenMod;
    double PercentSpellVampMod;
    double FlatMPRegenMod;
    double PercentDodgeMod;
    double FlatAttackSpeedMod;
    double FlatArmorMod;
    double FlatHPRegenMod;
    double PercentMagicDamageMod;
    double PercentMPPoolMod;
    double FlatMagicDamageMod;
    double PercentMPRegenMod;
    double PercentPhysicalDamageMod;
    double PercentHPPoolMod;
    double PercentArmorMod	;
    double PercentCritChanceMod;
    double PercentEXPBonus;
    double FlatHPPoolMod;
    double FlatCritChanceMod;
    double FlatEXPBonus;

    // **Objects contains image data.

    String full;
    String group;
    String spirte;
    int h;
    int w;
    int y;
    int x;

    // **Object contains item group data.

    String MaxGroupOwnable;
    String key;

};