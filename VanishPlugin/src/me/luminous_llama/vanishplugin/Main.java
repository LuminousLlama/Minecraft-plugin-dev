package me.luminous_llama.vanishplugin;


import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.luminous_llama.vanishplugin.commands.VanishCommand;
import me.luminous_llama.vanishplugin.events.JoinEvent;

public class Main extends JavaPlugin{

	public ArrayList<Player> vanishedPlayers = new ArrayList<>();
	
	@Override
	public void onEnable() {
		
		
		getCommand("vanish").setExecutor(new VanishCommand(this));
		
		getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
	}
}
