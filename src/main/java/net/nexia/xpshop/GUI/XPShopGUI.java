package net.nexia.xpshop.GUI;

import com.samjakob.spigui.SGMenu;
import com.samjakob.spigui.SpiGUI;
import com.samjakob.spigui.buttons.SGButton;
import net.nexia.nexiaapi.ItemsFromFile;
import net.nexia.nexiaapi.Processes;
import net.nexia.xpshop.Utilities.FileSetup;
import net.nexia.xpshop.XPShop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XPShopGUI
{

    SpiGUI spiGUI = new SpiGUI(XPShop.getMain());

    /**
     * The XPShop GUI.
     * Uses SpiGUI.
     * @param player The Player to show the GUI to.
     */
    public void openGUI(Player player)
    {
        //GUI Creation
        SGMenu xpShop = spiGUI.create("&6&lXPShop &f&6Page {currentPage}/{maxPage}", 5);

        //Button
        File[] itemFiles = FileSetup.xpShopItemsFolder.listFiles();

        //No Items Setup
        if (itemFiles == null)
        {
            player.sendMessage(Processes.color("&cXPShop Items folder is empty."));
            return;
        }

        for (File f : itemFiles)
        {
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
            List<String> sections = new ArrayList<>(yaml.getKeys(false));

            for (int i = 0; i < sections.size(); i++)
            {
                xpShop.setButton(i, new SGButton(ItemsFromFile.getItemFromSection(yaml.getConfigurationSection(sections.get(i) + ".Item"))));
            }
        }

        //Open GUI
        player.openInventory(xpShop.getInventory());
    }

}
