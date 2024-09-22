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
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilityRegistry;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.data.PlayerData;
import special.rpgplugin.data.statsClasses.CountableStatEnum;
import special.rpgplugin.data.statsClasses.StatsEnum;
import special.rpgplugin.managers.TeamManager;
import special.rpgplugin.utils.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Predicate;

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
        } else if (event.getMaterial() == Material.COMPASS && event.getAction().isRightClick()){
            player.sendMessage("Позиция: " + player.getLocation().getX() + ", " + player.getLocation().getY() + ", " + player.getLocation().getZ() + "\n" +
                    "Направление взгляда: " + player.getEyeLocation().getDirection().getX() + ", " + player.getEyeLocation().getDirection().getY() + ", " + player.getEyeLocation().getDirection().getZ() + "\n");
        }
    }

    @EventHandler
    private void onSpellUse(PlayerItemHeldEvent event) {
        PlayerWraper player = new PlayerWraper(event.getPlayer());

        if (player.getStateData().inSpellPick()) {
            if (event.getNewSlot() >= 0 || event.getNewSlot() < 4)
            AbilityRegistry.executeAbility(player.getBind(event.getNewSlot()+1), player);
            player.getStateData().setSpellPick(false);
            event.setCancelled(true);
        }
    }
    @EventHandler
    private void onItemSwap(PlayerSwapHandItemsEvent event){
        PlayerWraper player = new PlayerWraper(event.getPlayer());

        if (player.getStateData().inSpellPick()) {
            player.getStateData().setSpellPick(false);
        } else {
            if (Arrays.stream(player.getBinds()).anyMatch(s -> s != null)){
                player.getStateData().setSpellPick(true);
                player.player.getInventory().setHeldItemSlot(8);
            } else {
                player.sendMessage(ColorUtil.toColor("&cУ вас нет выбранных способностей."));
            }
        }
        event.setCancelled(true);
    }
    @EventHandler
    private void onDamage(EntityDamageByEntityEvent event){
        if (event.getDamager().getType() == EntityType.PLAYER){
            if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) || event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK)){
                event.getDamager().sendMessage("[DEBUG]: Урон до модификатора " + event.getDamage());
                event.setDamage(event.getDamage() * (new PlayerWraper((Player) event.getDamager()).getDamageModifier(StatsEnum.PHYSICAL_DAMAGE)));
                event.getDamager().sendMessage("[DEBUG]: Урон после модификатора " + event.getDamage());
            }
        }
        if (event.getEntity().getType() == EntityType.PLAYER){
            PlayerWraper player = new PlayerWraper((Player) event.getEntity());
            player.getCountableStat(CountableStatEnum.HEALTH).spend(event.getDamage());
            event.setDamage(0);
            HealthUtil.updateHealth(player);
        }
    }
}
