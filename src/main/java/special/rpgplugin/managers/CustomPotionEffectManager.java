package special.rpgplugin.managers;

import org.bukkit.entity.LivingEntity;
import special.rpgplugin.custom.potionEffect.CustomPotionEffect;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomPotionEffectManager {

    private final CustomPotionEffectManager instance = new CustomPotionEffectManager();
    private final Map<LivingEntity, List<CustomPotionEffect>> effectMap;

    private CustomPotionEffectManager(){
        effectMap = new HashMap<>();
    }

    public void addPotionEffect(LivingEntity entity, CustomPotionEffect effect){
        if (effectMap.containsKey(entity)){
            List<CustomPotionEffect> newList = effectMap.get(entity);
            newList.add(effect);
            effectMap.replace(entity, newList);
        } else {
            effectMap.put(entity, Collections.singletonList(effect));
        }
    }

    public CustomPotionEffectManager getInstance(){
        return instance;
    }
}
