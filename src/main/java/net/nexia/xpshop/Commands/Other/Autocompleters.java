package net.nexia.xpshop.Commands.Other;

import co.aikar.commands.BukkitCommandManager;
import net.nexia.xpshop.Utilities.FileSetup;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Autocompleters
{

    /**
     * Autocompleter for XPShop items.
     * @param manager The Command Manager from ACF.
     * @param id The Autocomplete ID. Must be either "tradingCardItems" or "mapArtItems".
     */
    public void itemsAutocomplete(BukkitCommandManager manager, String id)
    {
        manager.getCommandCompletions().registerCompletion(id, c ->
        {
            ArrayList<String> completer = new ArrayList<>();

            File[] itemFiles = FileSetup.xpShopItemsFolder.listFiles();
            if (itemFiles == null) return null;

            for (File f : itemFiles)
            {
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
                List<String> sections = new ArrayList<>(yaml.getKeys(false));

                completer.addAll(sections);
            }

            return completer;
        });
    }

}
