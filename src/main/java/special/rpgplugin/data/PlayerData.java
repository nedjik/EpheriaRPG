package special.rpgplugin.data;

import org.bukkit.entity.Player;
import special.rpgplugin.data.statsClasses.attributes.AttributeEnum;

public class PlayerData {

    public PlayerData(Player player){
        this.player = player;
        consistData = new ConsistData();
        statData = new StatData(player);
        playerState = new PlayerState(player);
    }

    private final Player player;
    private PlayerClass playerClass;
    private int playerLevel;
    private StatData statData;
    private ConsistData consistData;
    private PlayerState playerState;

    public PlayerClass getPlayerClass() {
        return playerClass;
    }
    public void setPlayerClass(PlayerClass playerClass) {
        this.playerClass = playerClass;
        statData.getAttribute(AttributeEnum.STRENGTH).setBaseValue(playerClass.getStartStrength());
        statData.getAttribute(AttributeEnum.DEXTERITY).setBaseValue(playerClass.getStartDexterity());
        statData.getAttribute(AttributeEnum.CONSTITUTION).setBaseValue(playerClass.getStartConstitution());
        statData.getAttribute(AttributeEnum.INTELLIGENCE).setBaseValue(playerClass.getStartIntelligence());
    }
    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    public StatData getStatData(){
        return statData;
    }
    public void setStatData(StatData statData){
        this.statData = statData;
    }
    public ConsistData getConsistData() {
        return consistData;
    }
    public void setConsistData(ConsistData consistData) {
        this.consistData = consistData;
    }
    public PlayerState getPlayerState(){
        return playerState;
    }
}
