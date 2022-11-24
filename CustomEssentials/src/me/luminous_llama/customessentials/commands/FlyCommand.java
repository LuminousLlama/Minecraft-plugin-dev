package me.luminous_llama.customessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.luminous_llama.customessentials.Main;
import me.luminous_llama.customessentials.utils.Utils;

public class FlyCommand implements CommandExecutor {

	private Main plugin;

	public FlyCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("fly").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
			return true;
		}

		Player p = (Player) sender;
		//p.sendMessage("agrs: " + args[0]);
		if (args.length >= 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null) { p.sendMessage(Utils.chat(plugin.getConfig().getString("FlyCommand.target_null"))); }
			else { 
				if (p.hasPermission("custome.fly")) {
					if (target.isFlying()) {
						target.setAllowFlight(false);
						target.setFlying(false);
						target.sendMessage(Utils.chat(plugin.getConfig().getString("FlyCommand.fly_target_disabled").replace("<player>", p.getName())));
						return true;
					} else {
						target.setAllowFlight(true);
						target.setFlying(true);
						target.sendMessage(Utils.chat(plugin.getConfig().getString("FlyCommand.fly_target_enabled").replace("<player>", p.getName())));
					}

				} else {
					p.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
				}
			}
		}else {
			p.sendMessage("Into else");
			if (p.hasPermission("custome.fly")) {
				if (p.isFlying()) {
					p.setAllowFlight(false);
					p.setFlying(false);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("FlyCommand.fly_disabled")));
					return true;
				} else {
					p.setAllowFlight(true);
					p.setFlying(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("FlyCommand.fly_enabled")));
				}

			} else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
			}

	}
		return false;
	}	
}
