package special.rpgplugin.custom.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class OnHealthUpdateEvent extends Event implements Cancellable {

    private Player player;
    private double oldCount;
    private double newCount;
    private boolean isCancelled;

    private static final HandlerList handlers = new HandlerList();

    public OnHealthUpdateEvent() {
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
