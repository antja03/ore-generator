package me.antja03.oregenerator;

import me.antja03.oregenerator.collection.WeightedList;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Anthony A.
 * 3/20/2020
 * 3:10 PM
 **/
public final class OreGenerator extends JavaPlugin
{
    public WeightedList<Material> oreMaterials;

    @Override
    public void onEnable()
    {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        oreMaterials = new WeightedList<>(Material.COBBLESTONE);
        oreMaterials.add(Material.EMERALD_ORE, getConfig().getDouble("emerald_chance") / 100);
        oreMaterials.add(Material.DIAMOND_ORE, getConfig().getDouble("diamond_chance") / 100);
        oreMaterials.add(Material.REDSTONE_ORE, getConfig().getDouble("redstone_chance") / 100);
        oreMaterials.add(Material.GOLD_ORE, getConfig().getDouble("gold_chance") / 100);
        oreMaterials.add(Material.IRON_ORE, getConfig().getDouble("iron_chance") / 100);
        oreMaterials.add(Material.COAL_ORE, getConfig().getDouble("coal_chance") / 100);

        getServer().getPluginManager().registerEvents(new BlockListener(this), this);
    }
}
