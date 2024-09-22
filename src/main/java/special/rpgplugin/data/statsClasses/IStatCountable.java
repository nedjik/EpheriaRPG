package special.rpgplugin.data.statsClasses;

import org.bukkit.entity.Player;

public abstract class IStatCountable extends IStat{

    private double count;
    public IStatCountable(String name, String colorTag, String symbol, double maxBaseValue, Player player) {
        super(name, colorTag, symbol, maxBaseValue, player);
        count = maxBaseValue;
    }

    public void setCount(double value){
        count = Math.min(value, getValue());
    }
    public double getCount(){
        return count;
    }

    public void restore(double amount){
        count = Math.min(getValue(), count + amount);
    }
    public void spend(double amount){
        count = Math.max(0, count - amount);
    }
    public void restore(double amount, boolean inPercent){
        if (inPercent){
            count = Math.min(getValue(), count + getValue()*(amount/100));
        } else {
            count = Math.min(getValue(), count + amount);
        }
    }
    public void spend(double amount, boolean inPercent){
        if(inPercent){
            count = Math.max(0, count - getValue()*(amount/100));
        } else {
            count = Math.max(0, count - amount);
        }
    }
}
