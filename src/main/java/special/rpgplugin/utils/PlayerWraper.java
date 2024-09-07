package special.rpgplugin.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import special.rpgplugin.data.*;
import special.rpgplugin.data.statsClasses.IStat;
import special.rpgplugin.data.statsClasses.StatsEnum;
import special.rpgplugin.data.statsClasses.attributes.AttributeEnum;

import java.util.UUID;

public class PlayerWraper {
    public final Player player;
    private final PlayerData playerData;
    private final ConsistData consistData;
    private final StatData statData;

    public PlayerWraper(Player player){
        this.player = player;
        this.playerData = PlayerUtil.getPlayerData(player);
        this.statData = PlayerUtil.getPlayerStatData(player);
        this.consistData = PlayerUtil.getPlayerConsistData(player);
    }

    public PlayerState getStateData(){
        return playerData.getPlayerState();
    }

    public UUID getPlayerTeamId(){
        return consistData.getTeamId();
    }
    public void setPlayerTeamId(UUID teamId){
        consistData.setTeamId(teamId);
    }

    public void setPlayerClass(PlayerClass playerClass){
        playerData.setPlayerClass(playerClass);
    }
    public PlayerClass getPlayerClass(){
        return playerData.getPlayerClass();
    }
    public int getPlayerLevel(){
        return playerData.getPlayerLevel();
    }
    public void levelUp(){
        playerData.setPlayerLevel(getPlayerLevel() + 1);
    }
    public void setPlayerLevel(int level){
        playerData.setPlayerLevel(level);
    }

    public boolean isOnline(){
        return player.isOnline();
    }
    public String getName(){
        return player.getName();
    }

    public IStat getStat(StatsEnum stat){
        return statData.getStat(stat);
    }
    public IStat getAttribute(AttributeEnum attribute){
        return statData.getAttribute(attribute);
    }
    public double getDamageModifier(StatsEnum stat){
        switch (stat){
            case PHYSICAL_DAMAGE -> {
                return statData.getStat(StatsEnum.PHYSICAL_DAMAGE).getValue();
            }
            case MAGIC_DAMAGE -> {
                return statData.getStat(StatsEnum.MAGIC_DAMAGE).getValue();
            }
        }
        return 1;
    }

    public UUID getUniqueId(){
        return player.getUniqueId();
    }
    public void sendMessage(String message){
        player.sendMessage(message);
    }
    public World getWorld(){
        return player.getWorld();
    }
    public Location getEyeLocation(){
        return player.getEyeLocation();
    }

    public Location getLocation() {
        return player.getLocation();
    }

    public double getHealth() {
        return player.getHealth();
    }
}
