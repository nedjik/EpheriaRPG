package special.rpgplugin.data;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import special.rpgplugin.other.ActionBarStateEnum;
import special.rpgplugin.utils.PlayerUtil;

import java.awt.*;
import java.util.function.Predicate;

public class PlayerState {

    public PlayerState(Player player) {
        this.player = player;
    }

    private final Player player;
    private boolean spellPick = false;
    private ActionBarStateEnum actionBarState = ActionBarStateEnum.BASE;

    public boolean inSpellPick() {
        return spellPick;
    }

    public void setSpellPick(boolean spellPick) {
        this.spellPick = spellPick;
        if (spellPick == true){
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).addModifier(new AttributeModifier("spellSlow", -0.9, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
            setActionBarState(ActionBarStateEnum.SPELL_PICK);
        } else {
            for (AttributeModifier attributeModifier: player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getModifiers()) {
                player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).removeModifier(attributeModifier);
                setActionBarState(ActionBarStateEnum.BASE);
            }
        }
    }

    public ActionBarStateEnum actionBarState(){
        return actionBarState;
    }
    public void setActionBarState(ActionBarStateEnum state){
        actionBarState = state;
    }
}
