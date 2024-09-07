package special.rpgplugin.ability.abilities;

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
import special.rpgplugin.utils.EntityUtil;
import special.rpgplugin.utils.PlayerWraper;

import java.util.List;

public class ArrowStorm extends ProjectileAbility {
    public ArrowStorm() {
        super(AbilitySelector.ENEMY, 0);
    }

    @Override
    public void execute(PlayerWraper player) {
        for(int i = 0; i < 5; i++){
            new BukkitRunnable(){
                @Override
                public void run() {
                    getShape().shoot(player.player, Arrow.class, getName());
                    player.getWorld().playSound(player.player, Sound.ENTITY_ARROW_SHOOT, 1, 1);
                }
            }.runTaskLater(Rpgplugin.getInstance(), i * 2);
        }
    }

    @Override
    public void executeOnEvent(ProjectileHitEvent event) {

    }

    @Override
    public void targetEffect(List<LivingEntity> entityList, Player player) {
        LivingEntity entity = entityList.get(0);
        entity.damage(3);
        entity.setNoDamageTicks(0);
        EntityUtil.knockback(player, entity, 0.3);
    }

    @Override
    public String getName() {
        return "ArrowStorm";
    }
}
