package special.rpgplugin.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import special.rpgplugin.data.*;
import special.rpgplugin.data.statsClasses.CountableStatEnum;
import special.rpgplugin.data.statsClasses.IStat;
import special.rpgplugin.data.statsClasses.IStatCountable;
import special.rpgplugin.data.statsClasses.StatsEnum;
import special.rpgplugin.data.statsClasses.attributes.AttributeEnum;
import special.rpgplugin.other.ActionBarStateEnum;

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

    public boolean bindAbility(int slot, String abilityName){
        return playerData.bindAbility(slot, abilityName);
    }
    public String getBind(int slot){
        return playerData.getBind(slot);
    }
    public String[] getBinds(){
        return playerData.getAbilityBinds();
    }

    public IStat getStat(StatsEnum stat){
        return statData.getStat(stat);
    }
    public IStatCountable getCountableStat(CountableStatEnum stat) {
        return statData.getCountableStat(stat);
    }
    public double getHealth() {
        return getCountableStat(CountableStatEnum.HEALTH).getCount();
    }
    public double getMana() {
        return getCountableStat(CountableStatEnum.MANA).getCount();
    }
    public void damage(double count) {
        getCountableStat(CountableStatEnum.HEALTH).spend(count);
    }
    public void damage(double count, boolean inPercent) {
        getCountableStat(CountableStatEnum.HEALTH).spend(count, inPercent);
    }
    public void spendMana(double count) {
        getCountableStat(CountableStatEnum.MANA).spend(count);
    }
    public void spendMana(double count, boolean inPercent) {
        getCountableStat(CountableStatEnum.MANA).spend(count, inPercent);
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

    public ActionBarStateEnum getABarState(){
        return getStateData().actionBarState();
    }
    public void setABarState(ActionBarStateEnum state){
        getStateData().setActionBarState(state);
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
    public boolean isOnline(){
        return player.isOnline();
    }
    public String getName(){
        return player.getName();
    }
    public Location getLocation() {
        return player.getLocation();
    }
}
