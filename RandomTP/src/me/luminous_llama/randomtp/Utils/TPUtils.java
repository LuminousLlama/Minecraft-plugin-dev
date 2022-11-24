package me.luminous_llama.randomtp.Utils;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.luminous_llama.randomtp.Main;

public class TPUtils {
	static Main plugin;

	public TPUtils(Main plugin) {
		TPUtils.plugin = plugin;
	}

	public static HashSet<Material> bad_blocks = new HashSet<>();

	static {
		bad_blocks.add(Material.LAVA);
		bad_blocks.add(Material.FIRE);
	}

	public static Location generateLocation(Player p) {

		double x = 0;
		double y = 0;
		double z = 0;

		if (plugin.getConfig().getBoolean("teleport-form-player")) {
			x = getRandomNumber((int) p.getLocation().getX(),
					(int) p.getLocation().getX() + (int) plugin.getConfig().getInt("player-teleport-distance"));
			z = getRandomNumber((int) p.getLocation().getZ(),
					(int) p.getLocation().getZ() + (int) plugin.getConfig().getInt("player-teleport-distance"));
			Random rand = new Random();
			float randNum = rand.nextFloat();
			if (randNum <= 0.25) {
				x *= -1;
				z *= -1;
			} else if (randNum > 0.25 && randNum <= 0.5) {
				x *= -1;
			} else if (randNum > 0.5 && randNum <= 0.75) {
				z *= -1;
			}
		} else if (plugin.getConfig().getBoolean("teleport-from-point")) {
			x = getRandomNumber(plugin.getConfig().getInt("teleport-point"),
					plugin.getConfig().getInt("teleport-point") + plugin.getConfig().getInt("point-teleport-distance"));
			z = getRandomNumber(plugin.getConfig().getInt("teleport-point"),
					plugin.getConfig().getInt("teleport-point") + plugin.getConfig().getInt("point-teleport-distance"));
			Random rand = new Random();
			float randNum = rand.nextFloat();
			if (randNum <= 0.25) {
				x *= -1;
				z *= -1;
			} else if (randNum > 0.25 && randNum <= 0.5) {
				x *= -1;
			} else if (randNum > 0.5 && randNum <= 0.75) {
				z *= -1;
			}
		}

		Location randomLocation = new Location(p.getWorld(), x, y, z);
		y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);
		randomLocation.setY(y);

		while (!isLocationSafe(randomLocation)) {
			randomLocation = generateLocation(p);
		}

		return randomLocation;
	}

	public static Location returnSafeLocation(Location location) {
		Location returnLocation = location;
		returnLocation.setX(location.getX() + 0.5);
		returnLocation.setY(location.getY() + 1);
		returnLocation.setZ(location.getZ() + 0.5);

		return returnLocation;
	}

	public static boolean isLocationSafe(Location location) {
		Block block = location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
		Block blockBelow = location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1,
				location.getBlockZ());
		Block blockAbove = location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() + 1,
				location.getBlockZ());

		return !(bad_blocks.contains(blockBelow.getType()) || bad_blocks.contains(block.getType())
				|| bad_blocks.contains(blockAbove.getType()));

	}

	public static int getRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	
	}
}
