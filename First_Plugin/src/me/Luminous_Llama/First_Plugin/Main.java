package me.Luminous_Llama.First_Plugin;

import org.bukkit.plugin.java.JavaPlugin;

import me.Luminous_Llama.First_Plugin.Commands.HelloCommand;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		new HelloCommand(this);
	}
	
	
}
