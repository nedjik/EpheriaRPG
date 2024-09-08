package special.rpgplugin.data.statsClasses.attributes;

import org.bukkit.entity.Player;
import special.rpgplugin.data.statsClasses.IStat;
import special.rpgplugin.data.statsClasses.StatsEnum;
import special.rpgplugin.utils.PlayerWraper;

public class Strength extends IStat {
    public Strength(Player player) {
        super("Strength", "&l&c", "", 10, player);
    }

    @Override
    protected void updateParam() {
        player.sendMessage("Сила изменена");
        new PlayerWraper(player).getStat(StatsEnum.PHYSICAL_DAMAGE).setBaseValue(1 + Math.max(0, Math.round((getBaseValue()/5-1)*10)/100));
    }
}
