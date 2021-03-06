package me.partlysunny.blocks.loot.entry.wand;

import me.partlysunny.ConsoleLogger;
import me.partlysunny.SimpleLuckyBlocksCore;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WandManager {

    private static final Map<String, Wand> wandTypes = new HashMap<>();

    public static void registerWand(String id, Wand e) {
        wandTypes.put(id, e);
    }

    public static void unregisterWand(String id) {
        wandTypes.remove(id);
    }

    public static Wand getWand(String id) {
        return wandTypes.get(id);
    }

    public static Set<String> wandKeys() {
        return wandTypes.keySet();
    }

    public static void loadWands() {
        wandTypes.clear();
        File dir = new File(JavaPlugin.getPlugin(SimpleLuckyBlocksCore.class).getDataFolder() + "/wands");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                loadWand(child.getName(), YamlConfiguration.loadConfiguration(child));
            }
        } else {
            ConsoleLogger.error("FATAL ERROR: wands directory not found");
        }
    }

    private static void loadWand(String childName, YamlConfiguration name) {
        int uses = name.getInt("uses");
        WandType t = WandType.valueOf(name.getString("wandType").toUpperCase());
        Material m = Material.getMaterial(name.getString("material").toUpperCase());
        registerWand(childName.substring(0, childName.length() - 4), new Wand(t, uses, m));
    }


}
