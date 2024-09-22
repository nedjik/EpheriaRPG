package special.rpgplugin.data.statsClasses;

import org.bukkit.entity.Player;
import special.rpgplugin.data.statsClasses.IStat;

public class Mana extends IStatCountable {
    public Mana(Player player) {
        super("Mana", "&b", "☆", 10, player);
    }

    @Override
    protected void updateParam() {

    }
}
