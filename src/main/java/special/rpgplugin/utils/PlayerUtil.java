package special.rpgplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import special.rpgplugin.data.ConsistData;
import special.rpgplugin.data.PlayerData;
import special.rpgplugin.data.StatData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerUtil {
    private static Map<String, PlayerData> playerDataMap = new HashMap<>();

    public static PlayerData getPlayerData(Player player){
        if (player != null){
            if (!playerDataMap.containsKey(player.getUniqueId().toString())){
                PlayerData playerData = new PlayerData(player);
                playerDataMap.put(player.getUniqueId().toString(), playerData);
                return playerData;
            }
            return playerDataMap.get(player.getUniqueId().toString());
        }
        return null;
    }

    public static PlayerData getPlayerData(UUID playerId){
        Player player = Bukkit.getPlayer(playerId);
        if (player != null){
            if (!playerDataMap.containsKey(player.getUniqueId().toString())){
                PlayerData playerData = new PlayerData(player);
                playerDataMap.put(player.getUniqueId().toString(), playerData);
                return playerData;
            }
            return playerDataMap.get(player.getUniqueId().toString());
        }
        return null;
    }
    public static void setPlayerData(Player player, PlayerData playerData){
        playerDataMap.put(player.getUniqueId().toString(), playerData);
    }
    public static void removePlayerData(Player player){
        playerDataMap.remove(player.getUniqueId().toString());
    }
    public static ConsistData getPlayerConsistData(UUID playerId){
        return getPlayerData(playerId).getConsistData();
    }
    public static ConsistData getPlayerConsistData(Player player){
        return getPlayerData(player).getConsistData();
    }
    private void setPlayerConsistData(Player player, ConsistData consistData){
        getPlayerData(player).setConsistData(consistData);
    }
    public static StatData getPlayerStatData(Player player){
        return getPlayerData(player).getStatData();
    }
    public static String getFolderPath(Player player){
        return Bukkit.getPluginsFolder().getAbsolutePath() + "/rpgplugin/" + "/player/" + player.getUniqueId();
    }
}
