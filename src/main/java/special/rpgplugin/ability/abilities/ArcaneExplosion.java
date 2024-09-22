package special.rpgplugin.ability.abilities;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import special.rpgplugin.Rpgplugin;
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.ability.AbilityShape;
import special.rpgplugin.particle.ParticleInstance;
import special.rpgplugin.particle.ParticleShape;
import special.rpgplugin.utils.PlayerWraper;

public class ArcaneExplosion extends Ability {
    public ArcaneExplosion() {
        super(AbilitySelector.ENEMY, AbilityShape.CIRCLE, "Взрыв арканы", 30, 0);
    }

    @Override
    public void execute(PlayerWraper player) {
        ParticleInstance particleInstance = new ParticleInstance(Particle.SPELL_WITCH, player.getLocation(), ParticleShape.CIRCLE, Math.round(Math.log10(player.getMana())) + 2);
        particleManager.onceDisplay(particleInstance);
        particleInstance = new ParticleInstance(Particle.SPELL_WITCH, player.getLocation(), 2, 100, 0.05);
        particleManager.onceDisplay(particleInstance);
        for (LivingEntity target : getSelector().filterTargets(player,getShape().getTargets(player.player, Math.log10(player.getMana()) + 2))) {
//            target.damage(10 + player.getMana()/10, player.player);
//            target.setLastDamageCause();
            EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(player.player, target, EntityDamageEvent.DamageCause.MAGIC, 10 + player.getMana()/10);
            Rpgplugin.getInstance().getServer().getPluginManager().callEvent(event);
            target.setLastDamageCause(event);
            target.setHealth(target.getHealth() - (10 + player.getMana()/10));
        }
        player.spendMana(player.getMana());
    }

    @Override
    public String getName() {
        return "ArcaneExplosion";
    }
}
