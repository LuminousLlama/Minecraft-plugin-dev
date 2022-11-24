package me.luminous_llama.titlepacket.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Utils {

	public static void tell(CommandSender sender,String message) {
		if(sender instanceof Player) {
			sender.sendMessage(message);
		}else {
			Bukkit.getConsoleSender().sendMessage(message);
		}
	}
	
	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
