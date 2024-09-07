package special.rpgplugin.ability.abilities;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.Utility;
import org.bukkit.entity.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import special.rpgplugin.ability.Ability;
import special.rpgplugin.ability.AbilitySelector;
import special.rpgplugin.ability.AbilityShape;
import special.rpgplugin.ability.ProjectileAbility;
import special.rpgplugin.utils.EntityUtil;
import special.rpgplugin.utils.PlayerWraper;

import java.util.Collections;
import java.util.List;

public class ManaBolt extends ProjectileAbility {
    public ManaBolt(){
        super(AbilitySelector.ENEMY, 0);
    }

    @Override
    public void execute(PlayerWraper player) {
        getShape().shoot(player.player, Snowball.class, getName());
        player.getWorld().playSound(player.player, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1.5f);
    }

    @Override
    public void executeOnEvent(ProjectileHitEvent event) {

    }

    @Override
    public void targetEffect(List<LivingEntity> entityList, Player player) {
        entityList.get(0).damage(5);
        EntityUtil.knockback(player, entityList.get(0), 1);
    }

    @Override
    public String getName() {
        return "ManaBolt";
    }
}
