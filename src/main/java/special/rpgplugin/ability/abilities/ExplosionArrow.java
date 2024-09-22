package special.rpgplugin.ability.abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import special.rpgplugin.Rpgplugin;
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.ability.AbilityShape;
import special.rpgplugin.ability.ProjectileAbility;
import special.rpgplugin.particle.ParticleInstance;
import special.rpgplugin.utils.EntityUtil;
import special.rpgplugin.utils.PlayerWraper;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.List;
import java.util.Locale;

public class ExplosionArrow extends ProjectileAbility {
    public ExplosionArrow() {
        super(AbilitySelector.ENEMY, 0, "Взрывная стрела", 10, 15);
    }

    @Override
    public void execute(PlayerWraper player) {
        getShape().shoot(player.player, Arrow.class, getName());
        player.getWorld().playSound(player.player, Sound.ENTITY_ARROW_SHOOT, 1, 1);
        player.getWorld().playSound(player.player, Sound.ENTITY_CREEPER_PRIMED, 1, 1.5f);
    }

    @Override
    public void executeOnEvent(ProjectileHitEvent event) {

    }

    @Override
    public void targetEffect(List<LivingEntity> entityList, Player player) {
        LivingEntity target = entityList.get(0);
        target.setMetadata("ExplosionArrowOn", new FixedMetadataValue(Rpgplugin.getInstance(), true));
        new BukkitRunnable(){
            @Override
            public void run() {
                if(!target.isDead()){
                    explosion(player, target);
                }
            }
        }.runTaskLater(Rpgplugin.getInstance(), 2 * 20);
    }

    @Override
    public String getName() {
        return "ExplosionArrow";
    }
    public void explosion(Player player, LivingEntity target){
        particleManager.onceDisplay(new ParticleInstance(Particle.EXPLOSION_LARGE, target.getLocation(), 0, 5, 0));
        target.getWorld().playSound(target, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
        getSelector().filterTargets(new PlayerWraper(player), target.getEyeLocation().getWorld().getNearbyLivingEntities(target.getEyeLocation(), 2).stream().toList()).forEach(entity -> {
            entity.damage(10);
            EntityUtil.knockback(target, entity, 1);
        });
    }
}
