package net.nexia.xpshop.GUI.Other;

import com.samjakob.spigui.buttons.SGButton;
import net.nexia.nexiaapi.Processes;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class XPShopGUIButton
{

    private final XPShopGUIEffects xpShopGUIEffects = new XPShopGUIEffects();

    public SGButton button(ItemStack item, Player player, int itemCost)
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

                    //Failed to purchase
                    if (remainingXP < 0 && playerGamemode != GameMode.CREATIVE)
                    {
                        xpShopGUIEffects.failedToPurchase(player);
                        return;
                    }

                    if (click == ClickType.LEFT) //Buy one unit
                    {
                        //Successfully purchased
                        if (playerGamemode != GameMode.CREATIVE)
                            player.setLevel(remainingXP);

                        Processes.giveToPlayer(player, clickedItem);
                    }
                    else
                    {
                        int j = 0;

                        while (remainingXP >= 0 && j < 64)
                        {
                            if (playerGamemode != GameMode.CREATIVE)
                                player.setLevel(remainingXP);

                            Processes.giveToPlayer(player, clickedItem);

                            currentLevel = player.getLevel();
                            remainingXP = currentLevel - itemCost;
                            j++;
                        }
                    }

                    xpShopGUIEffects.successfullyPurchased(player);
                });
    }

}
