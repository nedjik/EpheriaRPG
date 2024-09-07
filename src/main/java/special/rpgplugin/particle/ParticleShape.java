package special.rpgplugin.particle;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import special.rpgplugin.Rpgplugin;

public enum ParticleShape {
    CIRCLE {
        @Override
        public void display(ParticleInstance instance, World world) {
            int points = 30; // Количество точек на окружности
            double radius = instance.getRadius(); // Используем offsetX как радиус
            Location center = instance.getLocation();

            new BukkitRunnable() {
                int i = 0;

                @Override
                public void run() {
                    if (i >= points) {
                        this.cancel();
                        return;
                    }
                    double angle = 2 * Math.PI * i / points;
                    double x = center.getX() + radius * Math.cos(angle);
                    double z = center.getZ() + radius * Math.sin(angle);
                    Location point = new Location(world, x, center.getY(), z);
                    world.spawnParticle(instance.getType(), point, 1, 0, 0, 0, instance.getSpeed());
                    i++;
                }
            }.runTaskTimer(Rpgplugin.getInstance(), 0L, 20L / points);
        }
    },
    SQUARE {
        @Override
        public void display(ParticleInstance instance, World world) {
            double halfSide = instance.getRadius(); // Используем offsetX как половину стороны квадрата
            Location center = instance.getLocation();
            int pointsPerSide = 10;
            int totalPoints = pointsPerSide * 4;
            double step = halfSide * 2 / (pointsPerSide - 1);

            new BukkitRunnable() {
                int i = 0;

                @Override
                public void run() {
                    if (i >= totalPoints) {
                        this.cancel();
                        return;
                    }
                    double x = 0, z = 0;

                    if (i < pointsPerSide) {
                        x = -halfSide + i * step;
                        z = -halfSide;
                    } else if (i < pointsPerSide * 2) {
                        x = halfSide;
                        z = -halfSide + (i - pointsPerSide) * step;
                    } else if (i < pointsPerSide * 3) {
                        x = halfSide - (i - pointsPerSide * 2) * step;
                        z = halfSide;
                    } else {
                        x = -halfSide;
                        z = halfSide - (i - pointsPerSide * 3) * step;
                    }

                    Location point = new Location(world, center.getX() + x, center.getY(), center.getZ() + z);
                    world.spawnParticle(instance.getType(), point, 1, 0, 0, 0, instance.getSpeed());
                    i++;
                }
            }.runTaskTimer(Rpgplugin.getInstance(), 0L, 20L / totalPoints);
        }
    },
    LINE {
        @Override
        public void display(ParticleInstance instance, World world) {
            Location start = instance.getLocation();
            Location end = instance.getEndPoint();
            Boolean isInstantly = instance.isInstantly();
            int points = 20; // Количество точек на линии

            if (isInstantly == true){
                for (int i = 0; i < points; i++){
                    double t = (double) i / points;
                    double x = start.getX() + t * (end.getX() - start.getX());
                    double y = start.getY() + t * (end.getY() - start.getY());
                    double z = start.getZ() + t * (end.getZ() - start.getZ());
                    Location point = new Location(world, x, y, z);
                    world.spawnParticle(instance.getType(), point, 1, 0, 0, 0, instance.getSpeed());
                    i++;
                }
            } else{
                new BukkitRunnable() {
                    int i = 0;

                    @Override
                    public void run() {
                        if (i >= points) {
                            this.cancel();
                            return;
                        }
                        double t = (double) i / points;
                        double x = start.getX() + t * (end.getX() - start.getX());
                        double y = start.getY() + t * (end.getY() - start.getY());
                        double z = start.getZ() + t * (end.getZ() - start.getZ());
                        Location point = new Location(world, x, y, z);
                        world.spawnParticle(instance.getType(), point, 1, 0, 0, 0, instance.getSpeed());
                        i++;
                    }
                }.runTaskTimer(Rpgplugin.getInstance(), 0L, 20L / points);
            }
        }
    };

    public abstract void display(ParticleInstance instance, World world);
}
