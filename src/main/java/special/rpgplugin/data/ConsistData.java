package special.rpgplugin.data;

import java.util.Stack;
import java.util.UUID;

public class ConsistData {
    private Stack<UUID> teamInvites;
    private UUID teamId;
    private UUID battleId;
    private boolean isInBattle;
    private boolean isInTurn;

    public Stack<UUID> getTeamInvites() {
        return teamInvites;
    }
    public void setTeamInvites(Stack<UUID> teamInvites) {
        this.teamInvites = teamInvites;
    }
    public UUID getTeamId() {
        return teamId;
    }
    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }
    public UUID getBattleId() {
        return battleId;
    }
    public void setBattleId(UUID battleId) {
        this.battleId = battleId;
    }
    public boolean isInBattle() {
        return isInBattle;
    }
    public void setInBattle(boolean inBattle) {
        isInBattle = inBattle;
    }
    public boolean isInTurn() {
        return isInTurn;
    }
    public void setInTurn(boolean inTurn) {
        isInTurn = inTurn;
    }
}
