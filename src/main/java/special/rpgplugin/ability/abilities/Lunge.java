package special.rpgplugin.ability.abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.ProjectileHitEvent;
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.ability.AbilityShape;
import special.rpgplugin.particle.ParticleInstance;
import special.rpgplugin.utils.EntityUtil;
import special.rpgplugin.utils.PlayerWraper;

import java.util.List;

public class Lunge extends Ability {

    public Lunge(){
        super(AbilitySelector.ENEMY, AbilityShape.RAY);
    }
    @Override
    public void execute(PlayerWraper player) {
        List<LivingEntity> targets = getShape().getTargets(player.player, 3);

        Location endPoint = player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().normalize().multiply(3));
        super.particleManager.onceDisplay(new ParticleInstance(Particle.FIREWORKS_SPARK, player.getEyeLocation(), endPoint, true));

        EntityUtil.addForce(player, 1);

        if (getSelector().filterTargets(player, targets).size() > 0){
            for (LivingEntity target: getSelector().filterTargets(player, targets)) {
                target.damage(6);
                EntityUtil.knockback(player, target, 2);
            }
        }

        player.getWorld().playSound(player.player, Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1, 1f);
    }


    @Override
    public String getName() {
        return "Lunge";
    }
}
