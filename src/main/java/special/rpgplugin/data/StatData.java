package special.rpgplugin.data;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import special.rpgplugin.data.statsClasses.*;
import special.rpgplugin.data.statsClasses.attributes.*;
import special.rpgplugin.utils.PlayerUtil;

import java.util.Map;

public class StatData {

    public StatData(Player player){
        this.player = player;

        maxHealth = new Health(player);
        speed = new Speed(player);
        armor = new Armor(player);
        physicalDamage = new PhysicalDamage(player);
        magicDamage = new MagicDamage(player);
        cooldownReduction = new CooldownReduction(player);

        strength = new Strength(player);
        dexterity = new Dexterity(player);
        constitution = new Constitution(player);
        intelligence = new Intelligence(player);
    }
    private final Player player;

    private final Health maxHealth;
    private final Armor armor;
    private final Speed speed;
    private final PhysicalDamage physicalDamage;
    private final MagicDamage magicDamage;
    private final CooldownReduction cooldownReduction;

    private final Strength strength;
    private final Dexterity dexterity;
    private final Constitution constitution;
    private final Intelligence intelligence;

    private int totalPoints;
    private int freePoints;

    public IStat getStat(StatsEnum stat){
        return switch (stat) {
            case HEALTH -> maxHealth;
            case SPEED -> speed;
            case ARMOR -> armor;
            case PHYSICAL_DAMAGE -> physicalDamage;
            case MAGIC_DAMAGE -> magicDamage;
            case COOLDOWN_REDUCTION -> cooldownReduction;
        };
    }
    public IStat getAttribute(AttributeEnum attribute){
        return switch (attribute) {
            case STRENGTH -> strength;
            case DEXTERITY -> dexterity;
            case CONSTITUTION -> constitution;
            case INTELLIGENCE -> intelligence;
        };
    }
    public int getTotalPoints() {
        return totalPoints;
    }
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
    public int getFreePoints() {
        return freePoints;
    }
    public void setFreePoints(int freePoints) {
        this.freePoints = Math.min(totalPoints, freePoints);
    }
}
