package me.luminous_llama.deathmessages;

import org.bukkit.plugin.java.JavaPlugin;

import me.luminous_llama.deathmessages.listeners.PlayerDeathListener;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		new PlayerDeathListener(this);
	}
}
