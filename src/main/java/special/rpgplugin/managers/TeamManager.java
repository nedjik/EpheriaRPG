package special.rpgplugin.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import special.rpgplugin.instances.TeamInstance;
import special.rpgplugin.particle.ParticleManager;
import special.rpgplugin.utils.ColorUtil;
import special.rpgplugin.utils.PlayerUtil;
import special.rpgplugin.utils.PlayerWraper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TeamManager {

    private static final TeamManager instance = new TeamManager();
    private final Map<UUID, TeamInstance> teams;

    private TeamManager(){
        this.teams = new HashMap<>();
    }

    public UUID createTeam(String teamName){
        UUID teamId = UUID.randomUUID();
        TeamInstance teamInstance = new TeamInstance(this, teamId, teamName);
        teams.put(teamId, teamInstance);
        return teamId;
    }

    public void joinTeam(PlayerWraper player, UUID teamId){
        TeamInstance team = teams.get(teamId);
        if (team != null){
            if (player.getPlayerTeamId() == null){
                if (!team.getPlayerList().isEmpty()){
                    for (UUID playerId: team.getPlayerList()) {
                        PlayerWraper p = new PlayerWraper(Bukkit.getPlayer(playerId));
                        if (player.isOnline()){
                            p.sendMessage(ColorUtil.toColor("&a" + player.getName() + " вошёл в команду"));
                        }
                    }
                }
                team.addPlayer(player.getUniqueId());
                player.setPlayerTeamId(teamId);
                if (team.getPlayerList().size() == 1){
                    team.setOwner(player.getUniqueId());
                }
            }
        }
    }

    public void leaveTeam(PlayerWraper player) {
        UUID teamId = player.getPlayerTeamId();
        TeamInstance team = teams.get(teamId);
        if (team != null){
            team.removePlayer(player.player.getUniqueId());
            player.setPlayerTeamId(null);
            for (UUID playerId: team.getPlayerList()) {
                if (Bukkit.getPlayer(playerId).isOnline()){
                    Bukkit.getPlayer(playerId).sendMessage(ColorUtil.toColor("&a" + player.player.getName() + " покинул команду"));
                }
            }
            if (team.getPlayerList().size() == 1){
                team.setOwner(team.getPlayerList().get(0));
            } else if (team.getPlayerList().isEmpty()){
                teams.remove(teamId);
            }
        }
    }
    public void joinTeam(Player _player, UUID teamId){
        PlayerWraper player = new PlayerWraper(_player);
        TeamInstance team = teams.get(teamId);
        if (team != null){
            if (player.getPlayerTeamId() == null){
                if (!team.getPlayerList().isEmpty()){
                    for (UUID playerId: team.getPlayerList()) {
                        PlayerWraper p = new PlayerWraper(Bukkit.getPlayer(playerId));
                        if (player.isOnline()){
                            p.sendMessage(ColorUtil.toColor("&a" + player.getName() + " вошёл в команду"));
                        }
                    }
                }
                team.addPlayer(player.getUniqueId());
                player.setPlayerTeamId(teamId);
                if (team.getPlayerList().size() == 1){
                    team.setOwner(player.getUniqueId());
                }
            }
        }
    }

    public void leaveTeam(Player _player) {
        PlayerWraper player = new PlayerWraper(_player);
        UUID teamId = player.getPlayerTeamId();
        TeamInstance team = teams.get(teamId);
        if (team != null){
            team.removePlayer(player.player.getUniqueId());
            player.setPlayerTeamId(null);
            for (UUID playerId: team.getPlayerList()) {
                if (Bukkit.getPlayer(playerId).isOnline()){
                    Bukkit.getPlayer(playerId).sendMessage(ColorUtil.toColor("&a" + player.player.getName() + " покинул команду"));
                }
            }
            if (team.getPlayerList().size() == 1){
                team.setOwner(team.getPlayerList().get(0));
            } else if (team.getPlayerList().isEmpty()){
                teams.remove(teamId);
            }
        }
    }

    public List<UUID> getPlayerList(UUID teamId){
        TeamInstance team = teams.get(teamId);
        if (team != null){
            return team.getPlayerList();
        }
        return null;
    }

    public List<UUID> getPlayerList(TeamInstance team){
        if (team != null){
            return team.getPlayerList();
        }
        return null;
    }

    public TeamInstance getTeam(UUID teamId){
        TeamInstance team = teams.get(teamId);
        if (team != null){
            return team;
        }
        return null;
    }

    public void removeTeam(UUID teamId){
        TeamInstance team = teams.get(teamId);
        for (UUID playerId: team.getPlayerList()) {
            PlayerWraper player = new PlayerWraper(Bukkit.getPlayer(playerId));
            player.setPlayerTeamId(null);
            player.sendMessage(ColorUtil.toColor("&cКоманда распущена"));
        }

        teams.remove(teamId);
    }

    public void setOwner(UUID teamId, UUID playerId){
        TeamInstance team = teams.get(teamId);
        team.setOwner(playerId);
        for (UUID playerID: team.getPlayerList()) {
            PlayerWraper player = new PlayerWraper(Bukkit.getPlayer(playerID));
            player.sendMessage(ColorUtil.toColor("&a" + Bukkit.getPlayer(playerId).getName() + " назначен новым владельцем команды"));
        }
    }

    public UUID getOwner(UUID teamId){
        TeamInstance teamInstance = teams.get(teamId);
        return teamInstance.getOwnerUUID();
    }

    public static TeamManager getInstance(){
        return instance;
    }
}
