package special.rpgplugin.ability;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import special.rpgplugin.utils.PlayerWraper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ProjectileEvent implements Listener {
    @EventHandler
    private void onProjectileHit(ProjectileHitEvent event){
        Projectile projectile = event.getEntity();
        Location location = event.getEntity().getLocation();

        if (projectile.getMetadata("ability").get(0).asBoolean()){
            String abilityName = projectile.getMetadata("abilityName").get(0).asString();
            ProjectileAbility ability = (ProjectileAbility) AbilityRegistry.getAbility(abilityName);
            Player player = (Player) projectile.getShooter();
            List<LivingEntity> targets = new ArrayList<>();
            if (ability.getRadius() == 0){
                if (event.getHitEntity() != null){
                    targets = ability.getSelector().filterTargets(new PlayerWraper(player), Collections.singletonList((LivingEntity) event.getHitEntity()));
                    ability.targetEffect(targets, player);
                }
            } else {
                for (Entity entity : projectile.getNearbyEntities(ability.getRadius(), ability.getRadius(), ability.getRadius())) {
                    if (entity instanceof LivingEntity && !entity.equals(player)) {
                        targets.add((LivingEntity) entity);
                    }
                }
                targets = ability.getSelector().filterTargets(new PlayerWraper(player), targets);
                ability.targetEffect(targets, player);
            }
            ability.executeOnEvent(event);

            projectile.remove();
            event.setCancelled(true);
        }
    }
}
