package net.nexia.xpshop.GUI.Other;

import com.samjakob.spigui.buttons.SGButton;
import net.nexia.nexiaapi.Processes;
import net.nexia.xpshop.XPShop;
import org.bukkit.GameMode;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class XPShopGUIButton
{

    Configuration config = XPShop.getMain().getConfig();

    private final XPShopGUIEffects xpShopGUIEffects = new XPShopGUIEffects();

    public SGButton button(ItemStack item, Player player, int itemCost, String section)
    {
        return new SGButton(item)
                .withListener((InventoryClickEvent e) ->
                {
                    //Get the item
                    ItemStack currentItem = e.getCurrentItem();
                    if (currentItem == null) return;
                    ItemStack clickedItem = new ItemStack(currentItem);
                    ItemMeta clickedItemMeta = clickedItem.getItemMeta();
                    if (clickedItemMeta == null) return;
                    List<String> clickedItemLore = clickedItemMeta.getLore();
                    if (clickedItemLore == null) return;

                    //Clear the Extra Lore
                    if (clickedItemLore.size() < 5) return;
                    clickedItemLore.subList(clickedItemLore.size() - 5, clickedItemLore.size()).clear();
                    clickedItemMeta.setLore(clickedItemLore);

                    clickedItem.setItemMeta(clickedItemMeta);
                    
                    //Comparing
                    int currentLevel = player.getLevel();
                    ClickType click = e.getClick();
                    GameMode playerGamemode = player.getGameMode();

                    if (click != ClickType.LEFT && click != ClickType.SHIFT_LEFT) return;

                    int remainingXP = currentLevel - itemCost;

                    //Failed to purchase due to permission
                    if (config.getBoolean("PermissionBasedShop") && !player.hasPermission("net.nexia.xpshop." + section))
                    {
                        xpShopGUIEffects.failedToPurchase(player, "Permission");
                        return;
                    }

                    //Failed to purchase due to cost
                    if (remainingXP < 0 && playerGamemode != GameMode.CREATIVE)
                    {
                        xpShopGUIEffects.failedToPurchase(player, "Cost");
                        return;
                    }

                    if (click == ClickType.LEFT) //Buy one unit
                    {
                        //Successfully purchased
                        if (playerGamemode != GameMode.CREATIVE)
                            player.setLevel(remainingXP);

                        Processes.giveToPlayer(player, clickedItem);
                    }
                    else //Buy a stack
                    {
                        int j = 0;

                        if (playerGamemode != GameMode.CREATIVE)
                        {
                            while (remainingXP >= 0 && j < 64)
                            {
                                player.setLevel(remainingXP);

                                Processes.giveToPlayer(player, clickedItem);

                                currentLevel = player.getLevel();
                                remainingXP = currentLevel - itemCost;
                                j++;
                            }
                        }
                        else
                        {
                            for (int i = 0; i < 64; i++)
                                Processes.giveToPlayer(player, clickedItem);
                        }
                    }

                    xpShopGUIEffects.successfullyPurchased(player);
                });
    }

}
