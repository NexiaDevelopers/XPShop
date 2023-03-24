package net.nexia.xpshop.Utilities;

import net.nexia.xpshop.XPShop;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class FileSetup
{

    FileConfiguration config = XPShop.getMain().getConfig();

    //Folders
    public static File dataFolder = XPShop.getMain().getDataFolder();

    public static File xpShopItemsFolder = new File(dataFolder, "XPShopItems" + File.separator);

    /**
     * Setup for the basic files needed for the plugin to work.
     * @param javaPlugin The Plugin instance.
     */
    public FileSetup(JavaPlugin javaPlugin)
    {
        //Folders
        xpShopItemsFolder.mkdirs();

        //Example Files
        if (config.getBoolean("CreateExampleFiles"))
            javaPlugin.saveResource("XPShopItems" + File.separator + "ExampleItems.yml", true);
    }

}
