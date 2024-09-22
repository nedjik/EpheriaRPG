package special.rpgplugin.ability;

import org.bukkit.entity.Player;
import special.rpgplugin.ability.abilities.*;
import special.rpgplugin.utils.PlayerWraper;

import java.util.HashMap;
import java.util.Map;

public class AbilityRegistry {
    // Карта для хранения способностей по их именам
    private static final Map<String, Ability> abilities = new HashMap<>();

    // Регистрация новой способности
    public static void registerAbility(Ability ability) {
        abilities.put(ability.getName(), ability);
    }

    // Получение способности по имени
    public static Ability getAbility(String name) {
        return abilities.get(name);
    }

    // Выполнение способности по её имени и игроку
    public static void executeAbility(String name, PlayerWraper player) {
        if (name == null) return;
        Ability ability = getAbility(name);
        if (ability != null) {
            ability.execute(player);
        } else {
            player.sendMessage("Ability not found!");
        }
    }

    // Регистрация всех способностей в плагине
    public static void registerAllAbilities() {
        registerAbility(new Fireball());
        registerAbility(new SelfHeal());
        registerAbility(new Lunge());
        registerAbility(new ManaBolt());
        registerAbility(new AgroCry());
        registerAbility(new BattleCry());
        registerAbility(new ExplosionArrow());
        registerAbility(new ArrowStorm());
        registerAbility(new Dash());
        registerAbility(new ArcaneExplosion());
        // Здесь регистрируются другие способности
    }
}