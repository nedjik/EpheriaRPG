package special.rpgplugin.custom.potionEffect;

import org.bukkit.entity.LivingEntity;

public abstract class CustomPotionEffect {

    private double duration;
    private int power;

    public CustomPotionEffect(double duration, int power) {
        this.duration = duration;
        this.power = power;
    }

    public abstract void execute(LivingEntity entity);
    public abstract void onApply(LivingEntity entity);
    public abstract void onEnd(LivingEntity entity);

    public double getDuration() {
        return duration;
    }
    public int getPower() {
        return power;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }
    public void setPower(int power) {
        this.power = power;
    }
}
