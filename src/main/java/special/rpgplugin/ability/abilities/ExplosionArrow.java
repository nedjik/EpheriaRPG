package special.rpgplugin.ability.abilities;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import special.rpgplugin.Rpgplugin;
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.ability.AbilityShape;
import special.rpgplugin.ability.ProjectileAbility;
import special.rpgplugin.particle.ParticleInstance;
import special.rpgplugin.utils.EntityUtil;
import special.rpgplugin.utils.PlayerWraper;

import java.util.List;

public class ExplosionArrow extends ProjectileAbility {
    public ExplosionArrow() {
        super(AbilitySelector.ENEMY, 0);
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
        new BukkitRunnable(){
            @Override
            public void run() {
                if(!target.isDead()){
                    particleManager.onceDisplay(new ParticleInstance(Particle.EXPLOSION_LARGE, target.getLocation(), 0, 5, 0));
                    target.getWorld().playSound(target, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                    getSelector().filterTargets(new PlayerWraper(player), target.getLocation().getNearbyLivingEntities(2).stream().toList()).forEach(entity -> {
                        entity.damage(10);
                        EntityUtil.knockback(target, entity, 1);
                    });
                }
            }
        }.runTaskLater(Rpgplugin.getInstance(), 2 * 20);
    }

    @Override
    public String getName() {
        return "ExplosionArrow";
    }
}
