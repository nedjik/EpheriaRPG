package special.rpgplugin.instances;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import special.rpgplugin.managers.BoardManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ScoreboardInstance {

    private final BoardManager manager;
    private final String name;
    private final UUID scoreboardId;
    private final List<UUID> playerList;
    private final Scoreboard scoreboard;

    public ScoreboardInstance(BoardManager manager, String name, UUID scoreboardId){
        this.manager = manager;
        this.name = name;
        this.scoreboardId = scoreboardId;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.playerList = new ArrayList<UUID>();
    }
    public BoardManager getManager() {
        return manager;
    }

    public String getName() {
        return name;
    }

    public UUID getScoreboardId() {
        return scoreboardId;
    }

    public List<UUID> getPlayerList() {
        return playerList;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

}
