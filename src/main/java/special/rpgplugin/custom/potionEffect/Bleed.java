package special.rpgplugin.custom.potionEffect;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import special.rpgplugin.data.StatModifier;
import special.rpgplugin.data.statsClasses.HealthRegen;
import special.rpgplugin.data.statsClasses.IStat;
import special.rpgplugin.data.statsClasses.StatsEnum;
import special.rpgplugin.utils.PlayerWraper;

public class Bleed extends CustomPotionEffect{
    public double damage;
    public Bleed(double duration, int power, double damage) {
        super(duration, power);
        this.damage = damage;
    }

    @Override
    public void execute(LivingEntity entity) {
        if (entity.getType().equals(EntityType.PLAYER)){
            PlayerWraper player = new PlayerWraper((Player) entity);
            player.damage(damage * getPower());
        } else {
            entity.damage(damage * getPower());
        }
    }
    @Override
    public void onApply(LivingEntity entity) {
        switch (getPower()){
            case 2:
                if (entity.getType().equals(EntityType.PLAYER)){
                    PlayerWraper player = new PlayerWraper((Player) entity);
                    if (player.getStat(StatsEnum.HEALTH_REGEN).getModifier("bleed") == null){
                        StatModifier modifier = new StatModifier(-0.1, StatModifier.ModifierType.ADD);
                        player.getStat(StatsEnum.HEALTH_REGEN).addModifier("bleed", modifier);
                    } else {
                        player.getStat(StatsEnum.HEALTH_REGEN).getModifier("bleed").addValue(-0.1);
                    }
                }
                break;
        }
    }
    @Override
    public void onEnd(LivingEntity entity) {
        switch (getPower()) {
            case 2:
                if (entity.getType().equals(EntityType.PLAYER)) {
                    PlayerWraper player = new PlayerWraper((Player) entity);
                    IStat stat = player.getStat(StatsEnum.HEALTH_REGEN);
                    if (stat.getModifier("bleed") != null) {
                        stat.getModifier("bleed").addValue(0.1);
                        if (stat.getModifier("bleed").getValue() == 0) {
                            stat.removeModifier("bleed");
                        }
                    }
                    break;
                }
            case 3:
                break;
        }
    }
}
