package special.rpgplugin.data.statsClasses;

import org.bukkit.entity.Player;

public class HealthRegen extends IStat{
    public HealthRegen(Player player) {
        super("Health regeneration", "&b", "", 0.5, player);
    }

    @Override
    protected void updateParam() {

    }
}
