package special.rpgplugin.ability.abilities;

import org.bukkit.Sound;
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.utils.EntityUtil;
import special.rpgplugin.utils.PlayerWraper;

public class Dash extends Ability {
    public Dash() {
        super(AbilitySelector.SELF, null, "Рывок", 5, 0);
    }

    @Override
    public void execute(PlayerWraper player) {
        EntityUtil.addForce(player, 2.5);
        player.getWorld().playSound(player.player, Sound.BLOCK_POWDER_SNOW_BREAK, 3, 0.7f);
    }

    @Override
    public String getName() {
        return "Dash";
    }
}
