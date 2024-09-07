package special.rpgplugin.instances;

import special.rpgplugin.managers.TeamManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamInstance {

    private final TeamManager manager;
    private final UUID teamUUID;
    private List<UUID> playerList;
    private String teamName;
    private UUID ownerUUID;

    public TeamInstance(TeamManager manager, UUID teamUUID, String teamName) {
        this.manager = manager;
        this.teamUUID = teamUUID;
        this.teamName = teamName;
        this.playerList = new ArrayList<>();
        this.ownerUUID = null;
    }

    public void addPlayer (UUID playerId){
        if(!playerList.contains(playerId)){
            playerList.add(playerId);
        }
    }

    public void removePlayer (UUID playerId){
        playerList.remove(playerId);
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }
    public void setOwner(UUID ownerUUID){
        if (playerList.contains(ownerUUID)){
            this.ownerUUID = ownerUUID;
        }
    }

    public List<UUID> getPlayerList() {
        return playerList;
    }
    public String getTeamName(){
        return teamName;
    }
    public UUID getOwnerUUID(){
        return ownerUUID;
    }
}
