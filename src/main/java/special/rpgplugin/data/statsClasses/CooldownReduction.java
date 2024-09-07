package special.rpgplugin.data.statsClasses;

import org.bukkit.entity.Player;
import special.rpgplugin.utils.PlayerWraper;

public class CooldownReduction extends IStat{
    public CooldownReduction(Player player) {
        super("Cooldown reduction", "&e", "⌚", 0, player);
    }

    @Override
    protected void updateParam() {

    }
    public double getReduction(){
        return getValue();
    }
}
