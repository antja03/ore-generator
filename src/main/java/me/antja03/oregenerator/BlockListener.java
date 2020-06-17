package me.antja03.oregenerator;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.Random;

/**
 * Anthony A.
 * 3/20/2020
 * 3:15 PM
 **/
public class BlockListener implements Listener
{
    private OreGenerator plugin;
    private Random random;

    public BlockListener(OreGenerator plugin) {
        this.plugin = plugin;
        this.random = new Random();
    }

    @EventHandler
    public void OnBlockFromTo(BlockFromToEvent event)
    {
        // Check if the block that's flowing is lava and if that lava is flowing into an air block
        if (event.getBlock().getType().equals(Material.LAVA) && event.getToBlock().getType().equals(Material.AIR))
        {
            // Loop through all block faces
            for (BlockFace face : BlockFace.values())
            {
                // Check if the block touching [face] is a water block
                if (event.getToBlock().getRelative(face).getType() == Material.WATER)
                {
                    // The chance that an ore will be generated instead of cobblestone
                    double oreChance = random.nextDouble();

                    if (oreChance <= plugin.getConfig().getDouble("ore_chance") / 100)
                    {
                        World world = event.getToBlock().getWorld();
                        Location location = event.getToBlock().getLocation();
                        Material randomOre = plugin.oreMaterials.getRandom();

                        // Replace the air block with a random ore
                        world.getBlockAt(location).setType(randomOre);

                        // Play some effects
                        world.spawnParticle(Particle.SMOKE_LARGE, location.add(0, 1, 0), 5);
                        world.playSound(location, Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.0f);

                        // Play some extra effects if its diamond or emerald ore
                        if (randomOre.equals(Material.DIAMOND_ORE) || randomOre.equals(Material.EMERALD_ORE))
                        {
                            world.spawnParticle(Particle.EXPLOSION_NORMAL, location.add(0, 1, 0), 5);
                            world.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                        }

                        event.setCancelled(true);
                    }
                    break;
                }
            }
        }
    }
}
