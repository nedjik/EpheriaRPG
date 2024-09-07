package special.rpgplugin.data;

public class StatModifier {
    private double value;
    private final ModifierType modifier;

    public StatModifier(double value, ModifierType modifier){
        this.value = value;
        this.modifier = modifier;
    }

    public double getValue() {
        return value;
    }
    public ModifierType getModifier() {
        return modifier;
    }

    public void setValue(double newValue){
        value = newValue;
    }
    public void addValue(double value){
        this.value += value;
    }
    public void multValue(double value){
        this.value *= value;
    }

    public enum ModifierType{
        ADD,
        MULTIPLIER,
        MULTIPLIER_EXP;

    }
}
