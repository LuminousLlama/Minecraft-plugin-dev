package me.luminous_llama.vanishplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.luminous_llama.vanishplugin.Main;

public class JoinEvent implements Listener{

	Main plugin;
	public JoinEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		for(int i=0;i<plugin.vanishedPlayers.size();i++) {
			p.hidePlayer(plugin, plugin.vanishedPlayers.get(i));
			
		}
	}
}
