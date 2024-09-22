package special.rpgplugin.utils;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class EntityUtil {
    public static void knockback(LivingEntity player, LivingEntity entity, double force) {
        force = -force;
        entity.setVelocity(entity.getVelocity().add(new Vector(player.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize().multiply(force).getX(),
                0.25,
                player.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize().multiply(force).getZ())));
    }
    public static void addForce(LivingEntity entity, double force){
        Vector vector = entity.getVelocity().add(entity.getEyeLocation().getDirection().normalize().multiply(force));
        vector.setY(Math.max(0.1, Math.min(0.3, vector.getY())));
        entity.setVelocity(vector);
    }
    public static void knockback(PlayerWraper player, LivingEntity entity, double force) {
        force = -force;
        entity.setVelocity(entity.getVelocity().add(new Vector(player.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize().multiply(force).getX(),
                0.25,
                player.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize().multiply(force).getZ())));
    }
    public static void addForce(PlayerWraper player, double force){
        LivingEntity entity = player.player;
        Vector vector = entity.getVelocity().add(entity.getEyeLocation().getDirection().normalize().multiply(force));
        vector.setY(Math.max(0.1, Math.min(0.3, vector.getY())));
        entity.setVelocity(vector);

    }
}
