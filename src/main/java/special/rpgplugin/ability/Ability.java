package special.rpgplugin.ability;

import special.rpgplugin.particle.ParticleManager;
import special.rpgplugin.utils.PlayerWraper;

public abstract class Ability {

    public final ParticleManager particleManager = ParticleManager.getInstance();
    private final AbilitySelector selector;
    private final AbilityShape shape;
    private final String displayName;
    private final double cooldown;
    private final double mana_cost;

    public Ability(AbilitySelector selector, AbilityShape shape, String displayName, double cooldown, double manaCost) {
        this.selector = selector;
        this.shape = shape;
        this.displayName = displayName;
        this.cooldown = cooldown;
        mana_cost = manaCost;
    }

    public AbilitySelector getSelector() {
        return selector;
    }

    public AbilityShape getShape() {
        return shape;
    }

    public abstract void execute(PlayerWraper player);
    public abstract String getName();

    public String getDisplayName() {
        return displayName;
    }
    public double getCooldown() {
        return cooldown;
    }
    public double getMana_cost() {
        return mana_cost;
    }

}
