package me.luminous_llama.deathmessages.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.luminous_llama.deathmessages.Main;
import me.luminous_llama.deathmessages.utils.Utils;

public class PlayerDeathListener implements Listener{

	private Main plugin;
	
	public PlayerDeathListener(Main pulgin) {
		this.plugin = pulgin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		
		if(e.getEntity().getPlayer() instanceof Player);
		
			Player killer = e.getEntity().getKiller();
			Player p = e.getEntity();
			
		
			//e.setDeathMessage("&8[&a*&8] &7" + killer.getDisplayName() + " has killed &6" + p.getDisplayName());
			
			
			if(p == killer || killer == null) {
				p.sendMessage(Utils.chat("&8[&c*&8] &7 you killed yourself &6"));
			}else {
				killer.sendMessage(Utils.chat("&8[&a*&8] &7 you have killed &6" + p.getDisplayName()));
				p.sendMessage(Utils.chat("&8[&c*&8] &7 you have been killed by &6" + killer.getDisplayName()));
				
			}
			
			
			
	}
	

}
