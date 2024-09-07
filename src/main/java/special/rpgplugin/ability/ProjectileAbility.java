package special.rpgplugin.ability;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.List;

public abstract class ProjectileAbility extends Ability{

    private double radius;
    public ProjectileAbility(AbilitySelector selector, double radius) {
        super(selector, AbilityShape.PROJECTILE);
        this.radius = radius;
    }
    public abstract void executeOnEvent(ProjectileHitEvent event);
    public abstract void targetEffect(List<LivingEntity> entityList, Player player);

    public double getRadius(){
        return radius;
    }
}
