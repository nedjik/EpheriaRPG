package special.rpgplugin.data.statsClasses;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import special.rpgplugin.utils.PlayerWraper;

public class Health extends IStatCountable {
    public Health(Player player){
        super("Health", "&a", "❤", 10, player);
    }

    @Override
    protected void updateParam() {

    }
}
