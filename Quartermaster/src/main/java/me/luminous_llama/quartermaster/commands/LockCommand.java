package me.luminous_llama.quartermaster.commands;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.luminous_llama.quartermaster.QuartermasterMain;
import me.luminous_llama.quartermaster.utils.LockMenuSystem;
import me.luminous_llama.quartermaster.utils.LockUtils;
import net.md_5.bungee.api.ChatColor;

public class LockCommand implements CommandExecutor{

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Cannot execute command");
			return true;
		}
		
		Player p = (Player) sender;
		LockMenuSystem lockMenuSystem = QuartermasterMain.getPlayerMenuSystem(p);
	
		if(p.getTargetBlockExact(5) == null) {
			p.sendMessage("Cannot execute command");
			return true;
		}
		Block target;
		target = p.getTargetBlockExact(5);
		
		if(target.getType().equals(Material.CHEST)) { 
			if(LockUtils.isCurrentlyLocked(target)) {
				if(LockUtils.getWhoLocked(target).getUniqueId().equals(p.getUniqueId())) {
					p.sendMessage(ChatColor.GREEN + "You already locked this chest");
				}else {
					p.sendMessage(ChatColor.RED + "Chest already locked by " + ChatColor.YELLOW + LockUtils.getWhoLocked(target).getName());
				}
			}else {
				lockMenuSystem.setLockBlock(target);
				lockMenuSystem.openAskGUI();
			}
		}
		return true;
	}
	
	//----------------------------------------------------------------------------

//	public static Block getTarget() {
//		return target;
//	}
}
