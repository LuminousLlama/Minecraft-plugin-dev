package me.Luminous_Llama.First_Plugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Luminous_Llama.First_Plugin.Main;

public class HelloCommand implements CommandExecutor{
	
	private Main pulgin;
	
	public HelloCommand(Main plugin) {
		this.pulgin = plugin;
		plugin.getCommand("hello").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Only players can execute this command");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(p.hasPermission("hello.use")) {
			p.sendMessage("hi");
			return true;
		}else {
			p.sendMessage("you do not have permission to use this command");
		}
		
		
		return false;
	}
}
