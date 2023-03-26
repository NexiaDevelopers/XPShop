package net.nexia.xpshop.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.nexia.nexiaapi.ItemsFromFile;
import net.nexia.xpshop.GUI.XPShopGUI;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
@CommandAlias("xpshop")
public class XPShopCommands extends BaseCommand
{

    XPShopGUI xpShopGUI = new XPShopGUI();

    /**
     * The default /xpshop command.
     * Opens the XPShop GUI.
     * @param player The Player to open the GUI to.
     */
    @Default
    private void openXPShopGUI(Player player)
    {
        xpShopGUI.openGUI(player);
    }

    @Subcommand("buy")
    private void onBuySubcommand(Player player)
    {

    }

}
