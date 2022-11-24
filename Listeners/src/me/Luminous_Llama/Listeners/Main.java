package me.Luminous_Llama.Listeners;

import org.bukkit.plugin.java.JavaPlugin;

import me.Luminous_Llama.Listeners.Join.JoinListener;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		new JoinListener(this);
	}
}
