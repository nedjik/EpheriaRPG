package special.rpgplugin.ability.abilities;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.ability.AbilityShape;
import special.rpgplugin.particle.ParticleInstance;
import special.rpgplugin.utils.PlayerWraper;

import java.util.List;

public class AgroCry extends Ability {
    public AgroCry() {
        super(AbilitySelector.ENEMY, AbilityShape.CIRCLE);
    }

    @Override
    public void execute(PlayerWraper player) {
        List<LivingEntity> list = getSelector().filterTargets(player, getShape().getTargets(player.player, 5));
        list.forEach(entity -> {
            if (entity.getType().equals(EntityType.PLAYER)){
                entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 0, false, false, false));
            } else {
                ((Mob) entity).setTarget(player.player);
            }
        });

        player.getWorld().playSound(player.player, Sound.ITEM_GOAT_HORN_SOUND_5, 1, 2);
        particleManager.onceDisplay(new ParticleInstance(Particle.VILLAGER_ANGRY, player.getEyeLocation(), 0.1, 5, 0.5));
    }


    @Override
    public String getName() {
        return "AgroCry";
    }
}
