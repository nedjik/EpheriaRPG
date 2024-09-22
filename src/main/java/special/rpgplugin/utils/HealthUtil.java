package special.rpgplugin.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import special.rpgplugin.data.statsClasses.CountableStatEnum;
import special.rpgplugin.data.statsClasses.Health;

public class HealthUtil implements Listener {
    public static void updateHealth(PlayerWraper player){
        if (!player.player.isDead()){
            player.player.setHealth(player.player.getMaxHealth()/player.getCountableStat(CountableStatEnum.HEALTH).getValue()*player.getCountableStat(CountableStatEnum.HEALTH).getCount());
        }
    }
}
