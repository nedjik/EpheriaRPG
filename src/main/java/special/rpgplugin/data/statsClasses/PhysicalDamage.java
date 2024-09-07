package special.rpgplugin.data.statsClasses;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import special.rpgplugin.utils.PlayerWraper;

public class PhysicalDamage extends IStat{
    public PhysicalDamage(Player player) {
        super("Physical damage", "&a", "\uD83D\uDDE1",1, player);
    }

    @Override
    protected void updateParam() {

    }
}
