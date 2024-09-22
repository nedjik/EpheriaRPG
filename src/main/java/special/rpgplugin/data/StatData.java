package special.rpgplugin.data;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import special.rpgplugin.Rpgplugin;
import special.rpgplugin.data.statsClasses.*;
import special.rpgplugin.data.statsClasses.attributes.*;
import special.rpgplugin.utils.HealthUtil;
import special.rpgplugin.utils.PlayerWraper;

public class StatData {

    public StatData(Player player){
        this.player = player;

        health = new Health(player);
        mana = new Mana(player);
        healthRegen = new HealthRegen(player);
        manaRegen = new ManaRegen(player);
        speed = new Speed(player);
        armor = new Armor(player);
        physicalDamage = new PhysicalDamage(player);
        magicDamage = new MagicDamage(player);
        cooldownReduction = new CooldownReduction(player);

        strength = new Strength(player);
        dexterity = new Dexterity(player);
        constitution = new Constitution(player);
        intelligence = new Intelligence(player);

        regeneration();
    }
    private final Player player;

    private final Health health;
    private final Mana mana;
    private final HealthRegen healthRegen;
    private final ManaRegen manaRegen;
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

    public IStatCountable getCountableStat(CountableStatEnum stat){
        return switch (stat) {
            case HEALTH -> health;
            case MANA -> mana;
        };
    }
    public IStat getStat(StatsEnum stat){
        return switch (stat) {
            case SPEED -> speed;
            case ARMOR -> armor;
            case PHYSICAL_DAMAGE -> physicalDamage;
            case MAGIC_DAMAGE -> magicDamage;
            case COOLDOWN_REDUCTION -> cooldownReduction;
            case HEALTH_REGEN -> healthRegen;
            case MANA_REGEN -> manaRegen;
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

    private void regeneration(){
        new BukkitRunnable(){
            @Override
            public void run() {
                health.restore(healthRegen.getValue());
                HealthUtil.updateHealth(new PlayerWraper(player));
                mana.restore(Math.max(0.5, Math.round((manaRegen.getValue() * (mana.getCount()*2)/mana.getValue())*100)/100.0));
            }
        }.runTaskTimerAsynchronously(Rpgplugin.getInstance(), 10, 10);
    }
}
