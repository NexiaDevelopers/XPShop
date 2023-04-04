package net.nexia.xpshop.Utilities;

import net.nexia.xpshop.Events.NewVersionAvailability;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventRegistry
{

    /**
     * Registers all the Event Listeners.
     */
    public EventRegistry(JavaPlugin plugin)
    {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new NewVersionAvailability(), plugin);
    }

}
