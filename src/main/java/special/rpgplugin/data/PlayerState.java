package special.rpgplugin.data;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import special.rpgplugin.utils.PlayerUtil;

import java.awt.*;
import java.util.function.Predicate;

public class PlayerState {

    public PlayerState(Player player) {
        this.player = player;
    }

    private final Player player;
    private boolean spellPick = false;

    public boolean inSpellPick() {
        return spellPick;
    }

    public void setSpellPick(boolean spellPick) {
        this.spellPick = spellPick;
        if (spellPick == true){
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).addModifier(new AttributeModifier("spellSlow", -0.9, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
            player.sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(PlayerUtil.getPlayerData(player).getPlayerClass().getSpellListForActionBar()));
        } else {
            for (AttributeModifier attributeModifier: player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getModifiers()) {
                player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).removeModifier(attributeModifier);
            }
        }
    }
}
