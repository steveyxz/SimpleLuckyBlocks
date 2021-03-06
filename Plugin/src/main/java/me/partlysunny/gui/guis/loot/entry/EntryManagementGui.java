package me.partlysunny.gui.guis.loot.entry;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.gui.type.util.Gui;
import me.partlysunny.SimpleLuckyBlocksCore;
import me.partlysunny.blocks.loot.entry.LootEntryManager;
import me.partlysunny.gui.GuiInstance;
import me.partlysunny.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class EntryManagementGui implements GuiInstance {
    @Override
    public Gui getGui(HumanEntity p) {
        if (!(p instanceof Player player)) return new ChestGui(3, "");
        SimpleLuckyBlocksCore.reload();
        String[] values = LootEntryManager.getEntryKeys();
        ChestGui configListMenu = Util.getEntryManagement(player, "entryManagement", ChatColor.GOLD + "Manage Loot Entries", values, "entryCreation", "lootMenu");
        Util.setClickSoundTo(Sound.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF, configListMenu);
        return configListMenu;
    }
}
