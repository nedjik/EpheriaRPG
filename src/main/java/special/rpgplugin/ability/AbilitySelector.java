package special.rpgplugin.ability;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import special.rpgplugin.utils.PlayerWraper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum AbilitySelector {
    SELF {
        @Override
        public List<LivingEntity> filterTargets(PlayerWraper caster, List<LivingEntity> targets) {
            return Collections.singletonList(caster.player);
        }
    },
    ENEMY {
        @Override
        public List<LivingEntity> filterTargets(PlayerWraper caster, List<LivingEntity> targets) {
            List<LivingEntity> filteredTargets = new ArrayList<>();
            if (caster.getPlayerTeamId() != null){
                for (LivingEntity target : targets) {
                    if (target.getType().equals(EntityType.PLAYER)){
                        if (!target.equals(caster.player) && !caster.getPlayerTeamId().equals(new PlayerWraper((Player) target).getPlayerTeamId())){
                            filteredTargets.add(target);
                        }
                    } else {
                        filteredTargets.add(target);
                    }
                }
            } else {
                for (LivingEntity target : targets) {
                    if (!target.equals(caster.player)){
                        filteredTargets.add(target);
                    }
                }
            }
            return filteredTargets;
        }
    },
    ALLIES {
        @Override
        public List<LivingEntity> filterTargets(PlayerWraper caster, List<LivingEntity> targets) {
            List<LivingEntity> filteredTargets = new ArrayList<>();
            if (caster.getPlayerTeamId() != null){
                for (LivingEntity target : targets) {
                    if (target.getType().equals(EntityType.PLAYER)){
                        if (caster.getPlayerTeamId().equals(new PlayerWraper((Player) target).getPlayerTeamId())){
                            filteredTargets.add(target);
                        }
                    }
                }
            } else {
                filteredTargets.add(caster.player);
            }
            return filteredTargets;
        }
    },
    ALL {
        @Override
        public List<LivingEntity> filterTargets(PlayerWraper caster, List<LivingEntity> targets) {
            return targets; // Возвращаем все цели
        }
    };

    public abstract List<LivingEntity> filterTargets(PlayerWraper caster, List<LivingEntity> targets);
}

