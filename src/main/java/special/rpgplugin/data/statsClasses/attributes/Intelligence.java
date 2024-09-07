package special.rpgplugin.data.statsClasses.attributes;

import org.bukkit.entity.Player;
import special.rpgplugin.data.statsClasses.IStat;
import special.rpgplugin.data.statsClasses.MagicDamage;
import special.rpgplugin.data.statsClasses.StatsEnum;
import special.rpgplugin.utils.PlayerWraper;

public class Intelligence extends IStat {
    public Intelligence(Player player) {
        super("Intelligence", "&l&b", "", 10, player);
    }

    @Override
    protected void updateParam() {
        new PlayerWraper(player).getStat(StatsEnum.MAGIC_DAMAGE).setBaseValue(1 + Math.max(0, Math.round((getBaseValue()/5-1)*10)/100));
    }
}
