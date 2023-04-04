package net.nexia.xpshop;

import net.nexia.xpshop.Utilities.CommandRegistry;
import net.nexia.xpshop.Utilities.EventRegistry;
import net.nexia.xpshop.Utilities.FileSetup;
import org.bukkit.plugin.java.JavaPlugin;

public final class XPShop extends JavaPlugin
{

    //Main Instance
    private static XPShop xpShop;

    @Override
    public void onEnable()
    {
        //Main Instance
        xpShop = this;

        //Utilities
        new CommandRegistry(this);
        new EventRegistry(this);
        new FileSetup(this);

        //Config Access
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    //Main Instance
    public static XPShop getMain() { return xpShop; }

}
