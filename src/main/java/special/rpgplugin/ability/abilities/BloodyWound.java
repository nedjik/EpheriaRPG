package special.rpgplugin.ability.abilities;

import org.bukkit.entity.LivingEntity;
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.ability.AbilityShape;
import special.rpgplugin.utils.PlayerWraper;

public class BloodyWound extends Ability {
    public BloodyWound() {
        super(AbilitySelector.ENEMY, AbilityShape.RAY, "Кровавая рана", 5, 5);
    }

    @Override
    public void execute(PlayerWraper player) {
        for (LivingEntity entity: getSelector().filterTargets(player, getShape().getTargets(player.player, 3))) {

        }
    }

    @Override
    public String getName() {
        return "BloodyWound";
    }
}
