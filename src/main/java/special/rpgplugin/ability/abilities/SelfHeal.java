package special.rpgplugin.ability.abilities;

import org.bukkit.Particle;
import org.bukkit.Sound;
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.data.statsClasses.CountableStatEnum;
import special.rpgplugin.particle.ParticleInstance;
import special.rpgplugin.utils.PlayerWraper;

public class SelfHeal extends Ability {
    public SelfHeal(){
        super(AbilitySelector.SELF, null, "Лечение", 20, 15);
    }

    @Override
    public void execute(PlayerWraper player) {
        super.particleManager.onceDisplay(new ParticleInstance(Particle.VILLAGER_HAPPY, player.getLocation(), 0.5, 15));
        player.player.setHealth(Math.min(player.getHealth() + 6, player.getCountableStat(CountableStatEnum.HEALTH).getValue()));

        player.getWorld().playSound(player.player, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
    }

    @Override
    public String getName() {
        return "SelfHeal";
    }
}
