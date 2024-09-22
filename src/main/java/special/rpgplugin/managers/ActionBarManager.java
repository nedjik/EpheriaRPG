package special.rpgplugin.managers;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import special.rpgplugin.Rpgplugin;
import special.rpgplugin.ability.AbilityRegistry;
import special.rpgplugin.data.statsClasses.CountableStatEnum;
import special.rpgplugin.data.statsClasses.IStatCountable;
import special.rpgplugin.utils.ColorUtil;
import special.rpgplugin.utils.PlayerWraper;

public class ActionBarManager {

    private static final ActionBarManager instance = new ActionBarManager();
    private ActionBarManager(){

    }
    public void update(PlayerWraper player){
        switch (player.getABarState()){
            case BASE -> {
                IStatCountable health = player.getCountableStat(CountableStatEnum.HEALTH);
                IStatCountable mana = player.getCountableStat(CountableStatEnum.MANA);
                String text = ColorUtil.toColor(health.getColorTag() + health.getSymbol() + " " + health.getCount() + "/" + health.getValue() + "           " +
                        mana.getColorTag() + mana.getSymbol() + " " + mana.getCount() + "/" + mana.getValue());
                player.player.sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(text));
            }
            case SPELL_PICK ->  {

                String text = "";
                if (player.getBind(1) != null) {text += "&a[1] - " + AbilityRegistry.getAbility(player.getBind(1)).getDisplayName() + " ";}
                if (player.getBind(2) != null) {text += "&a[2] - " + AbilityRegistry.getAbility(player.getBind(2)).getDisplayName() + " ";}
                if (player.getBind(3) != null) {text += "&a[3] - " + AbilityRegistry.getAbility(player.getBind(3)).getDisplayName() + " ";}
                if (player.getBind(4) != null) {text += "&a[4] - " + AbilityRegistry.getAbility(player.getBind(4)).getDisplayName() + " ";}
                player.player.sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ColorUtil.toColor(text)));
            }
        }
    }

    public void updateCycle(){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p: Rpgplugin.getInstance().getServer().getOnlinePlayers()){
                    PlayerWraper player = new PlayerWraper(p);
                    update(player);
                }
            }
        }.runTaskTimerAsynchronously(Rpgplugin.getInstance(), 10, 10);
    }

    public static ActionBarManager getInstance(){
        return instance;
    }
}
