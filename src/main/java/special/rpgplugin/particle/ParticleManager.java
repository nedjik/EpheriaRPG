package special.rpgplugin.particle;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import special.rpgplugin.Rpgplugin;
import special.rpgplugin.utils.PlayerWraper;

import java.util.ArrayList;
import java.util.List;

public class ParticleManager {

    private static final ParticleManager instance = new ParticleManager();
    private List<ParticleInstance> particles;

    private ParticleManager() {
        this.particles = new ArrayList<>();
        startDisplayTask();
    }

    public ParticleInstance createParticle(Particle type, Location location, double speed, ParticleShape shape, double radius) {
        ParticleInstance instance = new ParticleInstance(type, location, speed, shape, radius);
        particles.add(instance);
        return instance;
    }
    public ParticleInstance createParticle(Particle type, Location location, ParticleShape shape, double radius) {
        ParticleInstance instance = new ParticleInstance(type, location, shape, radius);
        particles.add(instance);
        return instance;
    }
    public ParticleInstance createParticle(Particle type, Location location, double speed, Location endPoint) {
        ParticleInstance instance = new ParticleInstance(type, location, speed, endPoint);
        particles.add(instance);
        return instance;
    }
    public ParticleInstance createParticle(Particle type, Location location, Location endPoint, boolean isInstantly) {
        ParticleInstance instance = new ParticleInstance(type, location, endPoint, isInstantly);
        particles.add(instance);
        return instance;
    }

    public void removeParticle(ParticleInstance instance) {
        particles.remove(instance);
    }

    public void displayAll() {
        for (ParticleInstance particle : particles) {
            particle.display(particle.getLocation().getWorld());
        }
    }
    public void onceDisplay(ParticleInstance particle) {
        particle.display(particle.getLocation().getWorld());
    }

    // Остальные методы управления частицами
    public List<ParticleInstance> getParticles() {
        return particles;
    }
    private void startDisplayTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                displayAll();

            }
        }.runTaskTimerAsynchronously(Rpgplugin.getInstance(), 0L, 20L);  // Асинхронная задача с интервалом 1 секунда
    }

    public static ParticleManager getInstance(){
        return instance;
    }
}