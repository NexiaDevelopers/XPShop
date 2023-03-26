package net.nexia.xpshop.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.annotation.*;
import net.nexia.nexiaapi.ItemsFromFile;
import net.nexia.nexiaapi.Processes;
import net.nexia.xpshop.Commands.Other.Autocompleters;
import net.nexia.xpshop.GUI.Other.XPShopGUIEffects;
import net.nexia.xpshop.GUI.XPShopGUI;
import net.nexia.xpshop.Utilities.FileSetup;
import org.bukkit.GameMode;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
@CommandAlias("xpshop")
public class XPShopCommands extends BaseCommand
{

    private final XPShopGUIEffects xpShopGUIEffects = new XPShopGUIEffects();

    Autocompleters autocompleters = new Autocompleters();
    XPShopGUI xpShopGUI = new XPShopGUI();


    public XPShopCommands(BukkitCommandManager manager)
    {
        //Map Art Autocompleters
        autocompleters.itemsAutocomplete(manager, "xpshop-items");
    }

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
    @CommandPermission("net.nexia.xpshop.buy")
    @CommandCompletion("@xpshop-items")
    private void onBuySubcommand(Player player, String[] args)
    {
        File[] itemFiles = FileSetup.xpShopItemsFolder.listFiles();
        if (itemFiles == null) return;

        for (File f : itemFiles)
        {
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
            List<String> sections = new ArrayList<>(yaml.getKeys(false));

            for (String section : sections)
            {
                ConfigurationSection sectionInFile = yaml.getConfigurationSection(section);
                if (sectionInFile == null) return;

                int amountToBuy = 1;
                if (args.length >= 2)
                {
                    try
                    {
                        if (Integer.parseInt(args[1]) > 6400)
                        {
                            player.sendMessage(Processes.color("&cCan't give more than 6400 of &f[" + args[0] + "]&c."));
                            return;
                        }

                        amountToBuy = Integer.parseInt(args[1]);
                    }
                    catch (NumberFormatException e)
                    {
                        player.sendMessage(Processes.color("&cExpected Integer for last argument."));
                        return;
                    }
                }

                ItemStack item = ItemsFromFile.getItemFromSection(Objects.requireNonNull(sectionInFile.getConfigurationSection("Item")));
                int currentLevel = player.getLevel();
                int remainingXP = currentLevel - sectionInFile.getInt("Cost") * amountToBuy;
                GameMode playerGamemode = player.getGameMode();

                if (args[0].equals(section))
                {
                    if (playerGamemode != GameMode.CREATIVE)
                    {
                        if (remainingXP >= 0)
                        {
                            player.setLevel(remainingXP);
                            Processes.giveToPlayer(player, item);
                        }
                        else
                            xpShopGUIEffects.failedToPurchase(player);
                    }
                    else
                    {
                        for (int i = 0; i < amountToBuy; i++)
                            player.getInventory().addItem(item);
                    }

                    xpShopGUIEffects.successfullyPurchased(player);
                }
            }
        }
    }

}
