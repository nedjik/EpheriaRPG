package special.rpgplugin.data.statsClasses;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import special.rpgplugin.utils.PlayerWraper;

public class Armor extends IStat{

    public Armor(Player player) {
        super("Defence", "&7", "\uD83D\uDEE1", 0, player);
    }

    @Override
    protected void updateParam() {
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(getBaseValue());
    }
}
