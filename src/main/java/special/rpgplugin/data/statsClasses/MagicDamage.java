package special.rpgplugin.data.statsClasses;

import org.bukkit.entity.Player;
import special.rpgplugin.data.statsClasses.IStat;
import special.rpgplugin.utils.PlayerWraper;

public class MagicDamage extends IStat {
    public MagicDamage(Player player) {
        super("Magic damage", "&d", "☄", 1, player);
    }

    @Override
    protected void updateParam() {

    }

    public double getDamage(double damage){
        return damage * getValue();
    }
}
