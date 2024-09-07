package special.rpgplugin.ability.abilities;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.ability.AbilityShape;
import special.rpgplugin.ability.ProjectileAbility;
import special.rpgplugin.particle.ParticleInstance;
import special.rpgplugin.utils.EntityUtil;
import special.rpgplugin.utils.PlayerWraper;

import java.util.List;

public class Fireball extends ProjectileAbility {
    public Fireball() {
        super(AbilitySelector.ENEMY, 3);
    }

    @Override
    public void execute(PlayerWraper player) {
        getShape().shoot(player.player, org.bukkit.entity.Fireball.class, getName());
        player.getWorld().playSound(player.player, Sound.ENTITY_GHAST_SHOOT, 1, 1);
    }

    @Override
    public void executeOnEvent(ProjectileHitEvent event) {
        ParticleInstance particle = new ParticleInstance(Particle.FLAME, null, 0.2, 100, 0.5);
        particle.setLocation(event.getEntity().getLocation());
        particleManager.onceDisplay(particle);
    }

    @Override
    public void targetEffect(List<LivingEntity> entityList, Player player) {
        for (LivingEntity entity: entityList) {
            entity.damage(10);
            entity.setFireTicks(80);
            EntityUtil.knockback(player, entity, 1);
        }
    }

    @Override
    public String getName() {
        return "FireBall";
    }
}
