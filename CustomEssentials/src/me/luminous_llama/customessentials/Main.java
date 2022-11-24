package me.luminous_llama.customessentials;

import org.bukkit.plugin.java.JavaPlugin;

import me.luminous_llama.customessentials.commands.FlyCommand;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		new FlyCommand(this);
	}
}
