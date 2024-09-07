package special.rpgplugin.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public class ParticleInstance {

    private Particle type;
    private Location location;
    private double speed;
    private ParticleShape shape;
    private double radius;
    private Location endPoint;
    private double offSet;
    private int amount;

    private boolean instantly;

    public ParticleInstance(Particle type, Location location, double speed, Location endPoint) {
        this.type = type;
        this.location = location;
        this.speed = speed;
        this.shape = ParticleShape.LINE;
        this.endPoint = endPoint;
        this.offSet = 0;
        this.amount = 1;
    }

    public ParticleInstance(Particle type, Location location, Location endPoint, boolean isInstantly) {
        this.type = type;
        this.location = location;
        this.endPoint = endPoint;
        this.speed = 0;
        this.shape = ParticleShape.LINE;
        this.offSet = 0;
        this.amount = 1;
        this.instantly = isInstantly;
    }

    public ParticleInstance(Particle type, Location location, double offSet, int amount) {
        this.type = type;
        this.location = location;
        this.speed = 0;
        this.shape = null;
        this.offSet = offSet;
        this.amount = amount;
    }

    public ParticleInstance(Particle type, Location location, double offSet, int amount, double speed) {
        this.type = type;
        this.location = location;
        this.speed = speed;
        this.shape = null;
        this.offSet = offSet;
        this.amount = amount;
    }
    public ParticleInstance(Particle type, Location location, double speed, ParticleShape shape, double radius) {
        this.type = type;
        this.location = location;
        this.speed = speed;
        this.shape = shape;
        this.radius = radius;
        this.offSet = 0;
        this.amount = 1;
    }

    public ParticleInstance(Particle type, Location location, ParticleShape shape, double radius) {
        this.type = type;
        this.location = location;
        this.speed = 0;
        this.shape = shape;
        this.radius = radius;
        this.offSet = 0;
        this.amount = 1;
    }

    public void display(World world) {
        if (shape != null) {
            shape.display(this, world);
        } else {
            world.spawnParticle(type, location, amount, offSet, offSet, offSet, speed);
        }
    }

    // Getters and Setters будут добавлены вами
    public Particle getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    public double getSpeed() {
        return speed;
    }

    public ParticleShape getShape() {
        return shape;
    }

    public Location getEndPoint() {
        return endPoint;
    }

    public double getRadius(){
        return radius;
    }

    public boolean isInstantly() {
        return instantly;
    }

    public void setLocation(Location location){
        this.location = location;
    }
}

