package special.rpgplugin;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import special.rpgplugin.ability.AbilityRegistry;
import special.rpgplugin.data.PlayerClass;
import special.rpgplugin.data.StatData;
import special.rpgplugin.data.statsClasses.CountableStatEnum;
import special.rpgplugin.data.statsClasses.IStat;
import special.rpgplugin.data.statsClasses.IStatCountable;
import special.rpgplugin.data.statsClasses.StatsEnum;
import special.rpgplugin.data.statsClasses.attributes.AttributeEnum;
import special.rpgplugin.instances.TeamInstance;
import special.rpgplugin.managers.TeamManager;
import special.rpgplugin.utils.ColorUtil;
import special.rpgplugin.utils.PlayerUtil;
import special.rpgplugin.utils.PlayerWraper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class commandManager implements CommandExecutor {

    private final TeamManager teamManager = TeamManager.getInstance();
    private final Rpgplugin plugin;

    public commandManager(Rpgplugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (commandSender instanceof Player) {
            PlayerWraper player = new PlayerWraper((Player) commandSender);
            if (command.getName().equals("team")) {
                UUID teamId = player.getPlayerTeamId();
                switch (args[0]) {
                    case "create":
                        if (teamId == null) {
                            UUID newTeamId;
                            if (args.length == 1) {
                                newTeamId = teamManager.createTeam(player.getName() + " team");
                            } else {
                                newTeamId = teamManager.createTeam(args[1]);
                            }
                            player.sendMessage(ColorUtil.toColor("&aВы успешно создали команду!"));
                            teamManager.joinTeam(player, newTeamId);
                            return true;
                        } else {
                            player.sendMessage(ColorUtil.toColor("&cВы уже находитесь в команде"));
                            return true;
                        }
                    case "info":
                        if (teamId != null) {
                            TeamInstance team = teamManager.getTeam(teamId);
                            List<UUID> teamList = team.getPlayerList();
                            String playerListString = "";
                            playerListString += team.getTeamName() + "\n" +
                                    "Владелец команды:" + Bukkit.getPlayer(team.getOwnerUUID()).getName() + "\n" +
                                    "Участники команды:" + "\n";
                            for (UUID uuid : teamList) {
                                playerListString += Bukkit.getPlayer(uuid).getName() + "\n";
                            }
                            player.sendMessage(playerListString);
                            return true;
                        } else {
                            player.sendMessage(ColorUtil.toColor("&cУ вас нет команды"));
                            return true;
                        }
                    case "invite":
                        if (teamId != null) {
                            if (args.length == 1) {
                                player.sendMessage(ColorUtil.toColor("&cВы должны указать игрока"));
                                return true;
                            } else {
                                if (new PlayerWraper(Bukkit.getPlayer(args[1])).getPlayerTeamId() == null) {
                                    teamManager.joinTeam(new PlayerWraper(Bukkit.getPlayer(args[1])), teamId);
                                } else {
                                    player.sendMessage("Этот игрок уже находится в команде");
                                }
                                return true;
                            }
                        } else {
                            player.sendMessage(ColorUtil.toColor("&cУ вас нет команды"));
                            return true;
                        }
                    case "leave":
                        if (teamId != null) {
                            teamManager.leaveTeam(player);
                            player.sendMessage(ColorUtil.toColor("&aВы успешно покинули команду"));
                            return true;
                        } else {
                            player.sendMessage(ColorUtil.toColor("&cУ вас нет команды"));
                            return true;
                        }
                    case "kick":
                        if (teamId != null) {
                            if (teamManager.getOwner(teamId) == player.getUniqueId()) {
                                if (args.length != 1) {
                                    teamManager.leaveTeam(Bukkit.getPlayer(args[1]));
                                    return true;
                                } else {
                                    player.sendMessage(ColorUtil.toColor("&cВы должны указать игрока!"));
                                    return true;
                                }
                            } else {
                                player.sendMessage(ColorUtil.toColor("Вы должны быть владельцем команды"));
                                return true;
                            }
                        } else {
                            player.sendMessage(ColorUtil.toColor("&cУ вас нет команды"));
                            return true;
                        }
                    case "promote":
                        if (teamId != null) {
                            if (teamManager.getOwner(teamId) == player.getUniqueId()) {
                                if (args.length == 1) {
                                    player.sendMessage(ColorUtil.toColor("&cВы должны указать игрока"));
                                } else {
                                    teamManager.setOwner(teamId, Bukkit.getPlayer(args[1]).getUniqueId());
                                }
                                return true;
                            } else {
                                player.sendMessage(ColorUtil.toColor("Вы должны быть владельцем команды"));
                                return true;
                            }
                        } else {
                            player.sendMessage(ColorUtil.toColor("&cУ вас нет команды"));
                            return true;
                        }
                    case "disband":
                        if (teamId != null) {
                            if (teamManager.getOwner(teamId) == player.getUniqueId()) {
                                teamManager.removeTeam(teamId);
                                return true;
                            } else {
                                player.sendMessage(ColorUtil.toColor("&cВы должны быть владельцем команды"));
                                return true;
                            }
                        } else {
                            player.sendMessage(ColorUtil.toColor("&cУ вас нет команды"));
                            return true;
                        }
                }
            } else if (command.getName().equals("class")) {
                if (args.length != 0) {
                    //if (PlayerUtil.getPlayerData(player).getPlayerClass() == null){
                    if (true) {
                        switch (args[0]) {
                            case "warrior":
                                player.setPlayerClass(PlayerClass.WARRIOR);
                                return true;
                            case "ranger":
                                player.setPlayerClass(PlayerClass.RANGER);
                                return true;
                            case "mage":
                                player.setPlayerClass(PlayerClass.MAGE);
                                return true;
                        }
                    } else {
                        player.sendMessage(ColorUtil.toColor("&cУ вас уже есть класс!"));
                    }
                } else {
                    player.sendMessage(ColorUtil.toColor("&cВам нужно указать класс!"));
                }
            } else if (command.getName().equals("stat")) {
                if (args.length >= 3) {
                    player = new PlayerWraper(Bukkit.getPlayer(args[0]));
                    player.getAttribute(switch (args[1]) {
                        case "strength" -> AttributeEnum.STRENGTH;
                        case "dexterity" -> AttributeEnum.DEXTERITY;
                        case "constitution" -> AttributeEnum.CONSTITUTION;
                        case "intelligence" -> AttributeEnum.INTELLIGENCE;
                        default -> null;
                    }).setBaseValue(Integer.parseInt(args[2]));
                    return true;
                } else if (args.length == 2) {
                    player.getAttribute(switch (args[0]) {
                        case "strength" -> AttributeEnum.STRENGTH;
                        case "dexterity" -> AttributeEnum.DEXTERITY;
                        case "constitution" -> AttributeEnum.CONSTITUTION;
                        case "intelligence" -> AttributeEnum.INTELLIGENCE;
                        default -> AttributeEnum.STRENGTH;
                    }).setBaseValue(Integer.parseInt(args[1]));
                    return true;
                }
            } else if (command.getName().equals("info")) {
                if (args.length != 0) {
                    PlayerWraper player1 = new PlayerWraper(Bukkit.getPlayer(args[0]));
                    if (player1 != null) {
                        String infoMessage = "";
                        String playerClass;
                        if (player1.getPlayerClass() != null) {
                            playerClass = player.getPlayerClass().name();
                        } else {
                            playerClass = "Отсутствует";
                        }
                        infoMessage += "Никнейм: " + player1.getName() + "\n" +
                                "Класс: " + playerClass + "\n" +
                                "Сила: " + player1.getAttribute(AttributeEnum.STRENGTH).getBaseValue() + "\n" +
                                "Ловкость: " + player1.getAttribute(AttributeEnum.DEXTERITY).getBaseValue() + "\n" +
                                "Телосложение: " + player1.getAttribute(AttributeEnum.CONSTITUTION).getBaseValue() + "\n" +
                                "Интеллект: " + player1.getAttribute(AttributeEnum.INTELLIGENCE).getBaseValue() + "\n";
                        player.sendMessage(infoMessage);
                        return true;
                    } else {
                        player.sendMessage(ColorUtil.toColor("&cВам нужно указать игрока!"));
                        return true;
                    }
                } else {
                    String infoMessage = "";
                    String playerClass;
                    if (player.getPlayerClass() != null) {
                        playerClass = player.getPlayerClass().name();
                    } else {
                        playerClass = "Отсутствует";
                    }
                    infoMessage += "Никнейм: " + player.getName() + "\n" +
                            "Класс: " + playerClass + "\n" +
                            "Сила: " + player.getAttribute(AttributeEnum.STRENGTH).getBaseValue() + "\n" +
                            "Ловкость: " + player.getAttribute(AttributeEnum.DEXTERITY).getBaseValue() + "\n" +
                            "Телосложение: " + player.getAttribute(AttributeEnum.CONSTITUTION).getBaseValue() + "\n" +
                            "Интеллект: " + player.getAttribute(AttributeEnum.INTELLIGENCE).getBaseValue() + "\n";
                    player.sendMessage(infoMessage);

                    return true;
                }
            } else if (command.getName().equals("stats")) {
                if (args.length == 0) {
                    PlayerWraper user = player;

                    Inventory inventory = Bukkit.createInventory(null, 9, "Stats");

                    ItemStack attributeInfo = new ItemStack(Material.PLAYER_HEAD);
                    ItemMeta attributeMeta = attributeInfo.getItemMeta();
                    attributeMeta.setDisplayName(ChatColor.GOLD + "Attributes");
                    List<String> attributeLore = new ArrayList<>();
                    attributeLore.add(" ");
                    IStat attribute = player.getAttribute(AttributeEnum.STRENGTH);
                    attributeLore.add(ColorUtil.toColor(attribute.getColorTag() + attribute.getName() + ": &r" + attribute.getValue() + " &8(" + attribute.getBaseValue() + ")"));
                    attribute = player.getAttribute(AttributeEnum.DEXTERITY);
                    attributeLore.add(ColorUtil.toColor(attribute.getColorTag() + attribute.getName() + ": &r" + attribute.getValue() + " &8(" + attribute.getBaseValue() + ")"));
                    attribute = player.getAttribute(AttributeEnum.CONSTITUTION);
                    attributeLore.add(ColorUtil.toColor(attribute.getColorTag() + attribute.getName() + ": &r" + attribute.getValue() + " &8(" + attribute.getBaseValue() + ")"));
                    attribute = player.getAttribute(AttributeEnum.INTELLIGENCE);
                    attributeLore.add(ColorUtil.toColor(attribute.getColorTag() + attribute.getName() + ": &r" + attribute.getValue() + " &8(" + attribute.getBaseValue() + ")"));
                    attributeLore.add(" ");
                    attributeMeta.setLore(attributeLore);
                    attributeMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
                    attributeInfo.setItemMeta(attributeMeta);

                    ItemStack statInfo = new ItemStack(Material.NETHERITE_SWORD);
                    ItemMeta statMeta = statInfo.getItemMeta();
                    statMeta.setDisplayName(ChatColor.GOLD + "Stats");
                    List<String> statLore = new ArrayList<>();
                    statLore.add(" ");
                    IStatCountable countableStat = player.getCountableStat(CountableStatEnum.HEALTH);
                    statLore.add(ColorUtil.toColor(countableStat.getColorTag() + countableStat.getName() + ": &r" + countableStat.getCount() + "/" + countableStat.getValue()));
                    countableStat = player.getCountableStat(CountableStatEnum.MANA);
                    statLore.add(ColorUtil.toColor(countableStat.getColorTag() + countableStat.getName() + ": &r" + countableStat.getCount() + "/" + countableStat.getValue()));
                    IStat stat = player.getStat(StatsEnum.SPEED);
                    statLore.add(ColorUtil.toColor(stat.getColorTag() + stat.getName() + ": &r" + stat.getValue()));
                    stat = player.getStat(StatsEnum.ARMOR);
                    statLore.add(ColorUtil.toColor(stat.getColorTag() + stat.getName() + ": &r" + stat.getValue()));
                    stat = player.getStat(StatsEnum.PHYSICAL_DAMAGE);
                    statLore.add(ColorUtil.toColor(stat.getColorTag() + stat.getName() + ": &r" + stat.getValue()));
                    stat = player.getStat(StatsEnum.MAGIC_DAMAGE);
                    statLore.add(ColorUtil.toColor(stat.getColorTag() + stat.getName() + ": &r" + stat.getValue()));
                    stat = player.getStat(StatsEnum.COOLDOWN_REDUCTION);
                    statLore.add(ColorUtil.toColor(stat.getColorTag() + stat.getName() + ": &r" + stat.getValue()));
                    statLore.add(" ");
                    statMeta.setLore(statLore);
                    statMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
                    statInfo.setItemMeta(statMeta);

                    inventory.setItem(3, attributeInfo);
                    inventory.setItem(5, statInfo);

                    player.player.openInventory(inventory);
                    return true;
                }
            } else if (command.getName().equals("bind")) {
                if (args.length == 2) {
                    if (AbilityRegistry.getAbility(args[0]) != null) {
                        if (0 < Integer.parseInt(args[1]) && Integer.parseInt(args[1]) < 5) {
                            player.sendMessage(ColorUtil.toColor("&aВы успешно забиндили " + AbilityRegistry.getAbility(args[0]).getName() + " на " + Integer.parseInt(args[1]) + " слот"));
                            return player.bindAbility(Integer.parseInt(args[1]), args[0]);
                        } else {
                            player.sendMessage(ColorUtil.toColor("Вы можете указать лишь слоты от 1 до 4."));
                        }
                    } else {
                        player.sendMessage(ColorUtil.toColor("Вы неправильно указали способность!"));
                    }
                }
            } else {
                System.out.println("Эту команду может использовать только игрок!");
                return true;
            }
            return false;
        }



/*
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            Player sender = (Player) commandSender;
            if (command.getName().equals("move")){
                Location startPos = sender.getLocation();

                int particles = Integer.parseInt(strings[0]);
                int size = Integer.parseInt(strings[1]);

                for (int i = 0; i < particles; i++){
                    int finalI = i;
                    float timer = (float) i/particles;
                    getScheduler().scheduleSyncDelayedTask(getPluginManager().getPlugin("Rpgplugin"), new Runnable() {
                        @Override
                        public void run() {
                            double angle = (double) 360 / particles;
                            Location particleLoc = new Location(startPos.getWorld(), startPos.getX(), startPos.getY(), startPos.getZ());
                            particleLoc.setX(startPos.getX() + Math.cos((angle * finalI * Math.PI)/180) * size);
                            particleLoc.setZ(startPos.getZ() + Math.sin((angle * finalI * Math.PI)/180) * size);
                            startPos.getWorld().spawnParticle(Particle.FLAME, particleLoc, 0, 0, 0, 0, 1);
                        }
                    }, (long)(timer * 20));
                }
                return true;
            }
        }
        return false;
    }
*/
        return false;
    }
}
