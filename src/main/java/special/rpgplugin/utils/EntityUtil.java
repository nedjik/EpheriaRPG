package special.rpgplugin.utils;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class EntityUtil {
    public static void knockback(LivingEntity player, LivingEntity entity, double force) {
        force = -force;
        entity.setVelocity(entity.getVelocity().add(player.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize().multiply(force)));
    }
    public static void addForce(LivingEntity entity, double force){
        entity.setVelocity(entity.getVelocity().add(entity.getEyeLocation().getDirection().normalize().multiply(force)));
    }
    public static void knockback(PlayerWraper player, LivingEntity entity, double force) {
        force = -force;
        entity.setVelocity(entity.getVelocity().add(player.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize().multiply(force)));
    }
    public static void addForce(PlayerWraper player, double force){
        LivingEntity entity = player.player;
        entity.setVelocity(entity.getVelocity().add(entity.getEyeLocation().getDirection().normalize().multiply(force)));

    }
}
