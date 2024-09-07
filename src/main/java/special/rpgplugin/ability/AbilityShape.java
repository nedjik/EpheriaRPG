package special.rpgplugin.ability;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import special.rpgplugin.Rpgplugin;
import special.rpgplugin.particle.ParticleInstance;
import special.rpgplugin.particle.ParticleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public enum AbilityShape {

    RAY {
        @Override
        public List<LivingEntity> getTargets(Player caster, double range) {
            List<LivingEntity> targets = new ArrayList<>();
            Vector direction = caster.getEyeLocation().getDirection().normalize();
            Location currentLocation = caster.getLocation().clone();

            for (int i = 0; i < range; i++) {
                currentLocation.add(direction);
                for (Entity entity : currentLocation.getWorld().getNearbyEntities(currentLocation, 0.5, 0.5, 0.5)) {
                    if (entity instanceof LivingEntity && !targets.contains(entity)) {
                        targets.add((LivingEntity) entity);
                    }
                }
            }
            return targets;
        }
    },
    CIRCLE {
        @Override
        public List<LivingEntity> getTargets(Player caster, double radius) {
            List<LivingEntity> targets = new ArrayList<>();
            for (Entity entity : caster.getWorld().getNearbyEntities(caster.getLocation(), radius, radius, radius)) {
                if (entity instanceof LivingEntity && entity.getLocation().distance(caster.getLocation()) <= radius) {
                    targets.add((LivingEntity) entity);
                }
            }
            return targets;
        }
    },
    SQUARE {
        @Override
        public List<LivingEntity> getTargets(Player caster, double radius) {
            List<LivingEntity> targets = new ArrayList<>();
            Location startLocation = caster.getLocation();
            for (Entity entity : startLocation.getWorld().getNearbyEntities(startLocation, radius, radius, radius)) {
                Location loc = entity.getLocation();
                if (entity instanceof LivingEntity &&
                        Math.abs(loc.getX() - startLocation.getX()) <= radius &&
                        Math.abs(loc.getZ() - startLocation.getZ()) <= radius) {
                    targets.add((LivingEntity) entity);
                }
            }
            return targets;
        }
    },
    CONE {
        @Override
        public List<LivingEntity> getTargets(Player caster, double radius, double angle) {
            List<LivingEntity> targets = new ArrayList<>();
            Vector direction = caster.getEyeLocation().getDirection().normalize();
            Location startLocation = caster.getLocation();

            for (Entity entity : startLocation.getWorld().getNearbyEntities(startLocation, radius, radius, radius)) {
                if (entity instanceof LivingEntity) {
                    Vector toEntity = entity.getLocation().toVector().subtract(startLocation.toVector()).normalize();
                    double dotProduct = direction.dot(toEntity);

                    if (dotProduct >= Math.cos(Math.toRadians(angle / 2))) {
                        targets.add((LivingEntity) entity);
                    }
                }
            }
            return targets;
        }
    },
    TORUS {
        @Override
        public List<LivingEntity> getTargets(Player caster, double range, double radius, double angle) {
            List<LivingEntity> targets = new ArrayList<>();
            Location startLocation = caster.getLocation();
            double innerRadius = range;
            double outerRadius = radius;

            for (Entity entity : startLocation.getWorld().getNearbyEntities(startLocation, outerRadius, outerRadius, outerRadius)) {
                double distance = entity.getLocation().distance(startLocation);
                if (entity instanceof LivingEntity && distance >= innerRadius && distance <= outerRadius) {
                    targets.add((LivingEntity) entity);
                }
            }
            return targets;
        }
    },
    BROKEN_LINE {
        @Override
        public List<LivingEntity> getTargets(Player caster, double range) {
            List<LivingEntity> targets = new ArrayList<>();
            Location startLocation = caster.getEyeLocation();
            int segments = 3;  // количество сегментов в ломаной линии
            double segmentLength = range / segments;  // длина каждого сегмента

            // Заданные направления для каждого сегмента
            Vector[] directions = {
                    caster.getEyeLocation().getDirection(),             // Прямо вперед
                    caster.getEyeLocation().getDirection().rotateAroundY(Math.toRadians(45)),  // Поворот на 45 градусов вправо
                    caster.getEyeLocation().getDirection().rotateAroundY(Math.toRadians(-90))  // Поворот на 90 градусов влево
            };

            Location currentLocation = startLocation.clone();

            for (int i = 0; i < segments; i++) {
                Vector direction = directions[i % directions.length];
                RayTraceResult result = caster.getWorld().rayTraceEntities(currentLocation, direction, segmentLength, 0.2, entity -> entity instanceof LivingEntity);

                if (result != null) {
                    Entity hitEntity = result.getHitEntity();
                    if (hitEntity instanceof LivingEntity && !targets.contains(hitEntity)) {
                        targets.add((LivingEntity) hitEntity);
                    }
                }

                currentLocation.add(direction.multiply(segmentLength));
            }

            return targets;
        }
    },
    PROJECTILE {
//        @Override
//        public CompletableFuture<List<LivingEntity>> getTargetsAsync(Player caster, double radius, Class<? extends Projectile> projectileType, ParticleInstance particle) {
//            CompletableFuture<List<LivingEntity>> future = new CompletableFuture<>();
//
//            // Создание и запуск снаряда
//            Projectile projectile = caster.launchProjectile(projectileType);
//            if (projectileType.equals(Fireball.class)){
//                ((Fireball) projectile).setYield(0);
//            }
//            projectile.setVelocity(caster.getEyeLocation().getDirection().multiply(2));
//            projectile.setMetadata("id", new FixedMetadataValue(Rpgplugin.getInstance(),UUID.randomUUID()));
//
//            // Регистрация обработчика попадания снаряда
//            Bukkit.getPluginManager().registerEvents(new Listener() {
//                @EventHandler
//                public void onProjectileHit(ProjectileHitEvent event) {
//                    if (event.getEntity().equals(projectile)) {
//                        List<LivingEntity> targets = new ArrayList<>();
//
//                        // Получение места попадания
//                        Location hitLocation = event.getEntity().getLocation();
//
//                        // Если радиус больше 0, ищем цели в радиусе от места попадания
//                        if (radius > 0) {
//                            for (Entity entity : hitLocation.getWorld().getNearbyEntities(hitLocation, radius, radius, radius)) {
//                                if (entity instanceof LivingEntity && !entity.equals(caster)) {
//                                    targets.add((LivingEntity) entity);
//                                }
//                            }
//                        } else if (event.getHitEntity() instanceof LivingEntity) {
//                            // Если радиус 0, берем только ту сущность, в которую попал снаряд
//                            targets.add((LivingEntity) event.getHitEntity());
//                        }
//
//                        // Запуск переданного BukkitRunnable, если он есть
//                        if (particle != null) {
//                            particle.setLocation(hitLocation);
//                            ParticleManager.getInstance().onceDisplay(particle);
//                        }
//
//                        // Завершаем CompletableFuture с результатом
//                        future.complete(targets);
//
//                        // Отменяем регистрацию события
//                        event.getEntity().remove();
//                        event.setCancelled(true);
//                        HandlerList.unregisterAll(this);
//                    }
//                }
//            }, Rpgplugin.getInstance());
//
//            return future;
//        }
        @Override
        public void shoot(Player caster, Class<? extends Projectile> projectileClass, String abilityName){
            Projectile projectile = caster.launchProjectile(projectileClass);
            if (projectileClass.equals(Fireball.class)){
                ((Fireball) projectile).setYield(0);
            }
            projectile.setVelocity(caster.getEyeLocation().getDirection().multiply(2));
            projectile.setMetadata("ability", new FixedMetadataValue(Rpgplugin.getInstance(), true));
            projectile.setMetadata("abilityName", new FixedMetadataValue(Rpgplugin.getInstance(), abilityName));
        }
    };

    // Другие формы...
    public void shoot(Player caster, Class<? extends Projectile> projectileClass, String abilityName) {

    }
    public List<LivingEntity> getTargets(Player caster, double range, double radius, double angle) {
        return getTargets(caster, range);
    }
    public List<LivingEntity> getTargets(Player caster, double range) {
        return getTargets(caster, range);
    }
    public List<LivingEntity> getTargets(Player caster, double range, double angle) {
        return getTargets(caster, range, angle);
    }

    protected List<LivingEntity> getNearbyEntities(Location location, double radius) {
        List<LivingEntity> nearbyEntities = new ArrayList<>();
        for (Entity entity : location.getWorld().getNearbyEntities(location, radius, radius, radius)) {
            if (entity instanceof LivingEntity) {
                nearbyEntities.add((LivingEntity) entity);
            }
        }
        return nearbyEntities;
    }
}
