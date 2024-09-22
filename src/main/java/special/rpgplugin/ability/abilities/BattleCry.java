package special.rpgplugin.ability.abilities;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.ability.AbilityShape;
import special.rpgplugin.particle.ParticleInstance;
import special.rpgplugin.particle.ParticleShape;
import special.rpgplugin.utils.PlayerWraper;

import java.util.List;

public class BattleCry extends Ability {
    public BattleCry() {
        super(AbilitySelector.ALLIES, AbilityShape.CIRCLE, "Боевой клич", 15, 10);
    }

    @Override
    public void execute(PlayerWraper player) {
        List<LivingEntity> list = getSelector().filterTargets(player, getShape().getTargets(player.player, 4));
        list.forEach(entity -> {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10 * 20, 1, false, false));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10 * 20, 0, false, false));
        });

        player.getWorld().playSound(player.player, Sound.ITEM_GOAT_HORN_SOUND_0, 1, 1.2f);
        particleManager.onceDisplay(new ParticleInstance(Particle.FIREWORKS_SPARK, player.getLocation(), ParticleShape.CIRCLE, 4));
    }

    @Override
    public String getName() {
        return "BattleCry";
    }
}
