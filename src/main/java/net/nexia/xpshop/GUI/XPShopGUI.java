package net.nexia.xpshop.GUI;

import com.samjakob.spigui.SGMenu;
import com.samjakob.spigui.SpiGUI;
import net.nexia.nexiaapi.ItemsFromFile;
import net.nexia.nexiaapi.Processes;
import net.nexia.xpshop.GUI.Other.XPShopGUIButton;
import net.nexia.xpshop.Utilities.FileSetup;
import net.nexia.xpshop.XPShop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XPShopGUI
{

    private final XPShopGUIButton xpShopGUIButton = new XPShopGUIButton();

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

            int page = 0;
            int slot = 10;
            List<Integer> slotsToSkip = Arrays.asList(17, 18, 26, 27, 35, 36); //Slots to skip in order for Buttons to be centered
            for (int i = 0; i < sections.size(); i++)
            {
                //Centering Buttons
                if (slotsToSkip.contains(slot))
                {
                    Bukkit.broadcastMessage("test");
                    i--;
                    slot++;
                    continue;
                }

                ConfigurationSection itemSection = yaml.getConfigurationSection(sections.get(i) + ".Item");
                int itemCost = yaml.getInt(sections.get(i) + ".Cost");
                if (itemSection == null) continue;

                ItemStack item = ItemsFromFile.getItemFromSection(itemSection);

                //Setting the Lore
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta == null) continue;
                List<String> lore = itemMeta.getLore();
                if (lore == null) continue;
                lore.add("");
                lore.add(Processes.color("&7Cost: &6" + itemCost));
                lore.add("");
                lore.add(Processes.color("&7Click &8to purchase a unit."));
                lore.add(Processes.color("&7Shift-Click &8to purchase a stack."));
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);

                //Button
                xpShop.setButton(page, slot, xpShopGUIButton.button(item, player, itemCost));

                //Change Page if GUI is full
                if (i >= 45)
                {
                    slot = 0;
                    page++;
                }

                slot++;
            }
        }

        //Open GUI
        player.openInventory(xpShop.getInventory());
    }

}
