package special.rpgplugin.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import special.rpgplugin.data.PlayerClass;
import special.rpgplugin.data.PlayerData;
import special.rpgplugin.data.StatData;
import special.rpgplugin.data.statsClasses.attributes.AttributeEnum;

import java.io.File;
import java.io.IOException;

public class SaveUtil {
    public static void saveData(File filePath, Player player){
        FileConfiguration config = YamlConfiguration.loadConfiguration(filePath);
        PlayerData playerData = PlayerUtil.getPlayerData(player);
        StatData statData = playerData.getStatData();

        if (playerData.getPlayerClass() != null){
            config.set("player.base.class", playerData.getPlayerClass().name());
        }

        config.set("player.stats.characteristic.baseStrength", statData.getAttribute(AttributeEnum.STRENGTH).getBaseValue());
        config.set("player.stats.characteristic.baseDexterity", statData.getAttribute(AttributeEnum.DEXTERITY).getBaseValue());
        config.set("player.stats.characteristic.baseConstitution", statData.getAttribute(AttributeEnum.CONSTITUTION).getBaseValue());
        config.set("player.stats.characteristic.baseIntelligence", statData.getAttribute(AttributeEnum.INTELLIGENCE).getBaseValue());

        try {
            config.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(File filePath, Player player){
        FileConfiguration config = YamlConfiguration.loadConfiguration(filePath);
        PlayerData playerData = PlayerUtil.getPlayerData(player);
        StatData statData = playerData.getStatData();

        if (config.getString("player.base.class") != null){
            playerData.setPlayerClass(PlayerClass.valueOf(config.getString("player.base.class")));
        }

        statData.getAttribute(AttributeEnum.STRENGTH).setBaseValue(config.getInt("player.stats.characteristic.baseStrength"));
        statData.getAttribute(AttributeEnum.DEXTERITY).setBaseValue(config.getInt("player.stats.characteristic.baseDexterity"));
        statData.getAttribute(AttributeEnum.CONSTITUTION).setBaseValue(config.getInt("player.stats.characteristic.baseConstitution"));
        statData.getAttribute(AttributeEnum.INTELLIGENCE).setBaseValue(config.getInt("player.stats.characteristic.baseIntelligence"));
    }

    public static void createData(File filePath, Player player){
        FileConfiguration config = new YamlConfiguration();

        config.set("player.stats.characteristic.baseStrength", 0);
        config.set("player.stats.characteristic.baseDexterity", 0);
        config.set("player.stats.characteristic.baseConstitution", 0);
        config.set("player.stats.characteristic.baseIntelligence", 0);

        try {
            config.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
