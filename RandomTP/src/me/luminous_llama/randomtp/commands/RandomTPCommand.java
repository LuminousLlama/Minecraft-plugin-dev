package me.luminous_llama.randomtp.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.luminous_llama.randomtp.Utils.TPUtils;

public class RandomTPCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
		if(!(sender instanceof Player)){
			sender.sendMessage("Cannot excute this command");
			return true;
		}
		
		Player p = (Player) sender;
		
		p.teleport(TPUtils.returnSafeLocation(TPUtils.generateLocation(p)));
		
		return true;
	}
}
