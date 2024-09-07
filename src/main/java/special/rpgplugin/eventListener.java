package special.rpgplugin;

import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import special.rpgplugin.ability.AbilityRegistry;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.data.PlayerData;
import special.rpgplugin.data.statsClasses.StatsEnum;
import special.rpgplugin.managers.TeamManager;
import special.rpgplugin.utils.PlayerUtil;
import special.rpgplugin.utils.PlayerWraper;
import special.rpgplugin.utils.SaveUtil;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class eventListener implements Listener {

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        File playerConfig = new File(PlayerUtil.getFolderPath(player) + "/data.yml");

        if (playerConfig.exists()){
            SaveUtil.loadData(playerConfig, player);
        } else {
            SaveUtil.createData(playerConfig, player);
        }
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        TeamManager.getInstance().leaveTeam(player);

        File playerConfig = new File(PlayerUtil.getFolderPath(player) + "/data.yml");

        if (playerConfig.exists()){
            SaveUtil.saveData(playerConfig, player);
        } else {
            System.out.println("Игрок вышел и не имеет конфига");
        }

        PlayerUtil.removePlayerData(player);
    }

    @EventHandler
    private void onItemUse(PlayerInteractEvent event){
        PlayerWraper player = new PlayerWraper(event.getPlayer());

        if (event.getMaterial() == Material.BOOK && event.getAction().isRightClick()){
            AbilityRegistry.executeAbility(event.getItem().getItemMeta().getDisplayName(), player);
        }
    }

    @EventHandler
    private void onSpellUse(PlayerItemHeldEvent event) {
        PlayerWraper player = new PlayerWraper(event.getPlayer());

        if (player.getStateData().inSpellPick()) {
            switch (player.getPlayerClass()){
                case MAGE:
                    switch (event.getNewSlot()){
                        case 0:
                            AbilityRegistry.executeAbility("ManaBolt", player);
                            break;
                        case 1:
                            AbilityRegistry.executeAbility("FireBall", player);
                            break;
                        case 2:
                            AbilityRegistry.executeAbility("SelfHeal", player);
                            break;
                    }
                    break;
                case RANGER:
                    switch (event.getNewSlot()){
                        case 0:
                            AbilityRegistry.executeAbility("Dash", player);
                            break;
                        case 1:
                            AbilityRegistry.executeAbility("ArrowStorm", player);
                            break;
                        case 2:
                            AbilityRegistry.executeAbility("ExplosionArrow", player);
                            break;
                    }
                    break;
                case WARRIOR:
                    switch (event.getNewSlot()){
                        case 0:
                            AbilityRegistry.executeAbility("Lunge", player);
                            break;
                        case 1:
                            AbilityRegistry.executeAbility("AgroCry", player);
                            break;
                        case 2:
                            AbilityRegistry.executeAbility("BattleCry", player);
                            break;
                    }
                    break;
            }
            player.getStateData().setSpellPick(false);
            event.setCancelled(true);
        }
    }
    @EventHandler
    private void onItemSwap(PlayerSwapHandItemsEvent event){
        Player player = event.getPlayer();

        if (PlayerUtil.getPlayerData(player).getPlayerState().inSpellPick()) {
            PlayerUtil.getPlayerData(player).getPlayerState().setSpellPick(false);
        } else {
            PlayerUtil.getPlayerData(player).getPlayerState().setSpellPick(true);
            player.getInventory().setHeldItemSlot(8);
        }
        event.setCancelled(true);
    }
    @EventHandler
    private void onDamage(EntityDamageByEntityEvent event){
        if(event.getDamager().getType() == EntityType.PLAYER){
            if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) || event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK)){
                event.getDamager().sendMessage("[DEBUG]: Урон до модификатора " + event.getDamage());
                event.setDamage(event.getDamage() * (new PlayerWraper((Player) event.getDamager()).getDamageModifier(StatsEnum.PHYSICAL_DAMAGE)));
                event.getDamager().sendMessage("[DEBUG]: Урон после модификатора " + event.getDamage());
            }
        }
    }
}
