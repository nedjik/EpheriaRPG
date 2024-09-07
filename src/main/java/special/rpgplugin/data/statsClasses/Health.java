package special.rpgplugin.data.statsClasses;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import special.rpgplugin.utils.PlayerWraper;

public class Health extends IStat {
    public Health(Player player){
        super("Health", "&a", "❤", 10, player);
    }

    @Override
    protected void updateParam() {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(getBaseValue());
    }
}
