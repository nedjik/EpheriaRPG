package special.rpgplugin.data.statsClasses;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import special.rpgplugin.utils.PlayerWraper;

public class Speed extends IStat {
    public Speed(Player player) {
        super("Speed", "&b", "⭆", 1, player);
    }

    @Override
    protected void updateParam() {
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(getBaseValue()/10);
    }
}
