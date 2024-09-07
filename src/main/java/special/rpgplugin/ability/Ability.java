package special.rpgplugin.ability;

import org.bukkit.event.entity.ProjectileHitEvent;
import special.rpgplugin.particle.ParticleManager;
import special.rpgplugin.utils.PlayerWraper;

public abstract class Ability {

    public final ParticleManager particleManager = ParticleManager.getInstance();
    private final AbilitySelector selector;
    private final AbilityShape shape;

    public Ability(AbilitySelector selector, AbilityShape shape) {
        this.selector = selector;
        this.shape = shape;
    }

    public AbilitySelector getSelector() {
        return selector;
    }

    public AbilityShape getShape() {
        return shape;
    }

    public abstract void execute(PlayerWraper player);
    public abstract String getName();
}
