package special.rpgplugin.data.statsClasses;

import org.bukkit.entity.Player;

public class ManaRegen extends IStat{
    public ManaRegen(Player player) {
        super("Mana regeneration", "&b", "", 1, player);
    }

    @Override
    protected void updateParam() {

    }
}
