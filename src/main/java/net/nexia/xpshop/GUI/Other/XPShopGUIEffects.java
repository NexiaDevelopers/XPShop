package net.nexia.xpshop.GUI.Other;

import net.nexia.nexiaapi.Processes;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class XPShopGUIEffects
{

    /**
     * The Effects that take place when a Purchase is successful.
     * @param player The Player to play the Effects to.
     */
    public void successfullyPurchased(Player player)
    {
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3.0f, 0.5f);
    }

    /**
     * The Effects that take place when a Purchase fails.
     * @param player The Player to play the Effects to.
     */
    public void failedToPurchase(Player player, String failedType)
    {
        String version = Bukkit.getServer().getBukkitVersion().replace(".", "").split("-")[0];
        if (Integer.parseInt(version) >= 1140)  //For Versions 1.14 and up
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 3.0f, 0.5f);
        else
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 3.0f, 0.5f);

        if (failedType.equals("Cost"))
            player.sendMessage(Processes.color("&cYou do not have enough &7XP &cto buy."));
        else
            player.sendMessage(Processes.color("&cYou do not have the necessary permission to buy."));
    }

}
