package me.luminous_llama.vanishplugin.commands;



import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.luminous_llama.vanishplugin.Main;

public class VanishCommand implements CommandExecutor{

	Main plugin;
	
	public VanishCommand(Main plugin){
		this.plugin = plugin;
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("Cannot execute this command");
			return true;
		}
		
		Player p = (Player) sender;
		
		String name = p.getName();
		
		
		if(plugin.vanishedPlayers.contains(p)) {
			for(Player people: Bukkit.getOnlinePlayers()) {
				people.showPlayer(plugin,p);
			}
			plugin.vanishedPlayers.remove(p);
			p.sendMessage("You are now visible");
		}else if(!(plugin.vanishedPlayers.contains(p))) {
			for(Player people: Bukkit.getOnlinePlayers()) {
				people.hidePlayer(plugin,p);
			}
			plugin.vanishedPlayers.add(p);
			p.sendMessage("You are now invisible");
			
		}
		
		return false;
	}
	
}
