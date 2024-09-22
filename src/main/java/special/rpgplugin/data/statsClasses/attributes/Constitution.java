package special.rpgplugin.data.statsClasses.attributes;

import org.bukkit.entity.Player;
import special.rpgplugin.data.statsClasses.CountableStatEnum;
import special.rpgplugin.data.statsClasses.IStat;
import special.rpgplugin.data.statsClasses.StatsEnum;
import special.rpgplugin.utils.PlayerWraper;

public class Constitution extends IStat {
    public Constitution(Player player) {
        super("Constitution", "&l&e", "", 10, player);
    }

    @Override
    protected void updateParam() {
        new PlayerWraper(player).getCountableStat(CountableStatEnum.HEALTH).setBaseValue(15+(Math.floor(getBaseValue()-5)));
    }
}
