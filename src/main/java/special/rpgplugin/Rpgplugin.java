package special.rpgplugin;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import special.rpgplugin.ability.AbilityRegistry;
import special.rpgplugin.ability.ProjectileEvent;
import special.rpgplugin.managers.ActionBarManager;
import special.rpgplugin.managers.TeamManager;

public final class Rpgplugin extends JavaPlugin{

    private final TeamManager teamManager = TeamManager.getInstance();
    public Rpgplugin() {
    }

    @Override
    public void onEnable() {
        System.out.println("Started!");
        this.getCommand("team").setExecutor(new commandManager(this));
        this.getCommand("class").setExecutor(new commandManager(this));
        this.getCommand("stat").setExecutor(new commandManager(this));
        this.getCommand("info").setExecutor(new commandManager(this));
        this.getCommand("stats").setExecutor(new commandManager(this));
        this.getCommand("bind").setExecutor(new commandManager(this));

        this.getServer().getPluginManager().registerEvents(new eventListener(), this);
        this.getServer().getPluginManager().registerEvents(new ProjectileEvent(), this);

        ActionBarManager.getInstance().updateCycle();

        AbilityRegistry.registerAllAbilities();
    }

    @Override
    public void onDisable() {
        for (Player player: getServer().getOnlinePlayers()){
            player.kick(Component.newline().content("Сервер закрыт"));
        }
    }

    public static Rpgplugin getInstance(){
        return getPlugin(Rpgplugin.class);
    }
}
