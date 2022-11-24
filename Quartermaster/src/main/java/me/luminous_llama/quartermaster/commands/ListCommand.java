package me.luminous_llama.quartermaster.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.luminous_llama.quartermaster.QuartermasterMain;
import me.luminous_llama.quartermaster.utils.LockMenuSystem;

public class ListCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("cannot execute command");
		}
		
		Player p = (Player) sender;
		LockMenuSystem lockMenuSystem = QuartermasterMain.getPlayerMenuSystem(p);
		
		lockMenuSystem.openLocksListGUI();
		
		return false;
	}
}
