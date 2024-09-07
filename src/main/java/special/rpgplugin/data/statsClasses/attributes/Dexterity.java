package special.rpgplugin.data.statsClasses.attributes;

import org.bukkit.entity.Player;
import special.rpgplugin.data.statsClasses.IStat;
import special.rpgplugin.data.statsClasses.StatsEnum;
import special.rpgplugin.utils.PlayerWraper;

public class Dexterity extends IStat {
    public Dexterity(Player player) {
        super("Dexterity", "&l&a", "", 10, player);
    }

    @Override
    protected void updateParam() {
        new PlayerWraper(player).getStat(StatsEnum.SPEED).setBaseValue(Math.round((Math.pow(getBaseValue(),1/2.5)/2.511)*1000)/1000);
    }
}
