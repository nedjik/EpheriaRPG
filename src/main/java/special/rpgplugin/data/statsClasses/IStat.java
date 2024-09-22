package special.rpgplugin.data.statsClasses;

import org.bukkit.entity.Player;
import special.rpgplugin.data.PlayerClass;
import special.rpgplugin.data.StatModifier;
import special.rpgplugin.utils.PlayerWraper;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public abstract class IStat {
    private final String name;
    private final String colorTag;
    private final String symbol;
    private double baseValue;
    private double value;
    private final Map<String, StatModifier> modifierMap;
    protected final Player player;

    public IStat(String name, String colorTag, String symbol, double baseValue, Player player){
        this.name = name;
        this.colorTag = colorTag;
        this.symbol = symbol;
        this.baseValue = baseValue;
        this.value = baseValue;
        this.modifierMap = new HashMap<>();
        this.player = player;
    }

    public String getName(){
        return name;
    }
    public String getColorTag(){
        return colorTag;
    }
    public String getSymbol(){
        return symbol;
    }

    public void setBaseValue(double baseValue){
        this.baseValue = baseValue;
        updateValue();
    }
    public double getBaseValue(){
        return baseValue;
    }
    public double getValue() {
        return value;
    }
    private void updateValue(){
        double add = 0;
        double multiplier = 1;
        double multiplier_exp = 1;
        for (StatModifier modifier: modifierMap.values()) {
            switch (modifier.getModifier()){
                case ADD:
                    add += modifier.getValue();
                    break;
                case MULTIPLIER:
                    multiplier += modifier.getValue() / 100;
                    break;
                case MULTIPLIER_EXP:
                    multiplier_exp *= 1 + modifier.getValue() / 100;
                    break;
            }
        }
        value = ((baseValue + add) * (multiplier)) * multiplier_exp;
        updateParam();
    }
    protected abstract void updateParam();

    public StatModifier getModifier(String name){
        return modifierMap.get(name);
    }
    public void addModifier(String name, StatModifier modifier){
        modifierMap.put(name, modifier);
        updateValue();
    }
    public void removeModifier(String name){
        modifierMap.remove(name);
        updateValue();
    }
    public void setModifier(String name, double newValue){
        getModifier(name).setValue(newValue);
        updateValue();
    }
}
