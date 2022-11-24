package me.luminous_llama.armorstandgui.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.luminous_llama.armorstandgui.utils.Utils;

public class ArmorStandCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) { 
			sender.sendMessage("Console cannot execute this command");
			return true;
		}
		
		Utils.openMainMenu((Player) sender);
		
		return true;
	}
}
