package net.nexia.xpshop.Utilities;

import co.aikar.commands.BukkitCommandManager;
import net.nexia.xpshop.Commands.XPShopCommands;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegistry
{

    /**
     * Registers all the Commands.
     */
    public CommandRegistry(JavaPlugin plugin)
    {
        BukkitCommandManager manager = new BukkitCommandManager(plugin);
        manager.registerCommand(new XPShopCommands());
    }

}
